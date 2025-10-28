package com.golfpvcc.peep

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class PeepApplication

fun main(args: Array<String>) {
	runApplication<PeepApplication>(*args)
}
