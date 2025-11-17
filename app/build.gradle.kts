plugins {
    id("peep.spring-boot-app")
}

group = "com.golfpvcc"
version = "0.0.1-SNAPSHOT"
description = "Version 2 of peep"

dependencies {
    implementation(projects.user)
    implementation(projects.chat)
    implementation(projects.notification)
    implementation(projects.common)
    implementation(libs.kotlin.reflect)
    implementation(libs.spring.boot.starter.security)

    implementation(libs.spring.boot.starter.mail)

    implementation(libs.spring.boot.starter.amqp)
    implementation(libs.spring.boot.starter.data.redis)
    implementation(libs.spring.boot.starter.data.jpa)
    runtimeOnly(libs.postgresql)
}
