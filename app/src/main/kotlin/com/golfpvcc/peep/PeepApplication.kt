package com.golfpvcc.peep

import com.golfpvcc.peep.infra.database.entities.UserEntity
import com.golfpvcc.peep.infra.database.repositories.UserRepository
import jakarta.annotation.PostConstruct
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.stereotype.Component

@SpringBootApplication
class PeepApplication

fun main(args: Array<String>) {
	runApplication<PeepApplication>(*args)
}

@Component // test if the database can store a record
class Vinnie (
    private val repository: UserRepository
) {
    @PostConstruct
    fun init(){
        repository.save(
            UserEntity(
                email = "gmail.com",
                username = "loft",
                hashedPassword = "1234"
            )
        )
    }
}