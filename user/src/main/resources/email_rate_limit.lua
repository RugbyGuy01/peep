--[[
------------------------------------------------------------------------------
This is explanation how Redis logic works

Attempt #1 First failure

-- State before:
-- attemptCountKey doesn't exist
-- rateLimitKey doesn't exist

-- Execution:
if redis.call('EXISTS', rateLimitKey) == 1 then  -- FALSE, skip
...
end

currentCount = 0  -- No previous attempts
newCount = 1      -- INCR sets it to 1
backoffIndex = math.min(0, 2) + 1 = 1
-- SETEX rateLimitKey, 60, '1'  (locked for 60 seconds)
-- EXPIRE attemptCountKey, 86400

return { 1, 0 }  -- OUTPUT: [1, 0]

## Meaning: "This is your 1st attempt, now locked for 60 seconds" ##
------------------------------------------------------------------------------

Attempt #2 Tries again during rate limit

-- State before:
-- rateLimitKey EXISTS (still within 60 seconds)
-- attemptCountKey = "1"

-- Execution:
if redis.call('EXISTS', rateLimitKey) == 1 then  -- TRUE
local ttl = redis.call('TTL', rateLimitKey)  -- e.g., 42 seconds left
return { -1, 42 }  -- OUTPUT: [-1, 42]
end
-- Script ends here, rest doesn't execute

## Meaning: "You're blocked! Wait 42 more seconds" ##

------------------------------------------------------------------------------

Attempt #3 Tries again AFTER 60 seconds expired

-- State before:
-- rateLimitKey doesn't exist (expired after 60s)
-- attemptCountKey = "1" (still exists, 24h not expired)

-- Execution:
if redis.call('EXISTS', rateLimitKey) == 1 then  -- FALSE, skip
...
end

currentCount = 1  -- Previous attempt count
newCount = 2      -- INCR sets it to 2
backoffIndex = math.min(1, 2) + 1 = 2
-- SETEX rateLimitKey, 300, '1'  (locked for 300 seconds = 5 min)
-- EXPIRE attemptCountKey, 86400

return { 2, 0 }  -- OUTPUT: [2, 0]

## Meaning: "This is your 2nd attempt, now locked for 300 seconds = 5 min" ##

------------------------------------------------------------------------------

Attempt #4 Tries during 5-minute lock

-- State before:
-- rateLimitKey EXISTS (within 300 seconds)
-- attemptCountKey = "2"

-- Execution:
if redis.call('EXISTS', rateLimitKey) == 1 then  -- TRUE
local ttl = redis.call('TTL', rateLimitKey)  -- e.g., 218 seconds left
return { -1, 218 }  -- OUTPUT: [-1, 218]
end

## Meaning: "You're blocked! Wait 218 more seconds 3.6 min" ##

------------------------------------------------------------------------------

Attempt #5 Tries again AFTER 5 minutes expired

-- State before:
-- rateLimitKey doesn't exist (expired after 300s)
-- attemptCountKey = "2"

-- Execution:
if redis.call('EXISTS', rateLimitKey) == 1 then  -- FALSE, skip
...
end

currentCount = 2  -- Previous attempt count
newCount = 3      -- INCR sets it to 3
backoffIndex = math.min(2, 2) + 1 = 3
-- SETEX rateLimitKey, 3600, '1'  (locked for 3600 seconds = 1 hour)
-- EXPIRE attemptCountKey, 86400

return { 3, 0 }  -- OUTPUT: [3, 0]

## Meaning: "This is your 3rd attempt, now locked for 3600 seconds 1 hour" ##

------------------------------------------------------------------------------

Attempt #6+ Any future attempts after 1 hour

-- Same as attempt #5, but:
currentCount = 3 (or 4, 5, etc.)
newCount = 4 (or 5, 6, etc.)
backoffIndex = math.min(3, 2) + 1 = 3  -- Still capped at 3!
-- SETEX rateLimitKey, 3600, '1'  (always 1 hour max)

return { 4, 0 }  -- OUTPUT: [4, 0], [5, 0], etc.

## Meaning: "4th+ attempt, always locked for 1 hour max penalty" ##

]]

local rateLimitKey = KEYS[1]
local attemptCountKey = KEYS[2]

if redis.call('EXISTS', rateLimitKey) == 1 then
    local ttl = redis.call('TTL', rateLimitKey)
    return {-1, ttl > 0 and ttl or 60 }
end

local currentCount = redis.call('GET', attemptCountKey)
currentCount = currentCount and tonumber(currentCount) or 0

local newCount = redis.call('INCR', attemptCountKey)

local backoffSeconds = {60, 300, 3600}
local backoffIndex = math.min(currentCount, 2) + 1

redis.call('SETEX', rateLimitKey, backoffSeconds[backoffIndex], '1')

redis.call('EXPIRE', attemptCountKey, 86400)

return { newCount, 0 }