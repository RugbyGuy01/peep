package com.golfpvcc.peep.infra.database.repositories

import com.golfpvcc.peep.infra.database.entities.EmailVerificationTokenEntity
import com.golfpvcc.peep.infra.database.entities.UserEntity
import org.springframework.data.jpa.repository.JpaRepository
import java.time.Instant

interface EmailVerificationTokenRepository: JpaRepository<EmailVerificationTokenEntity, Long> {
    fun findByToken(token: String): EmailVerificationTokenEntity?
    fun deleteByExpiresAtLessThan(now: Instant)
    fun findByUserAndUsedAtIsNull(user: UserEntity): List<EmailVerificationTokenEntity>
}