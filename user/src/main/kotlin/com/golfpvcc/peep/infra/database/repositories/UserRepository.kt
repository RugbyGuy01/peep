package com.golfpvcc.peep.infra.database.repositories

import com.golfpvcc.peep.domain.model.UserId
import com.golfpvcc.peep.infra.database.entities.UserEntity
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository: JpaRepository<UserEntity, UserId> {
    fun findByEmail(email: String): UserEntity?
    fun findByEmailOrUsername(email: String, username: String): UserEntity?
}