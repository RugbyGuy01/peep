package com.golfpvcc.peep

import com.golfpvcc.peep.infra.database.entities.UserEntity
import com.golfpvcc.peep.infra.database.repositories.UserRepository

import jakarta.annotation.PostConstruct
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.EnableScheduling
import org.springframework.stereotype.Component

@SpringBootApplication
@EnableScheduling
class ChirpApplication

fun main(args: Array<String>) {
    runApplication<ChirpApplication>(*args)
}