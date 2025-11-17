package com.golfpvcc.peep.domain.events

import java.time.Instant

interface PeepEvent {
    val eventId: String
    val eventKey: String
    val occurredAt: Instant
    val exchange: String
}