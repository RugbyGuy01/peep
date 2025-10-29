plugins {
    id("peep.spring-boot-app")
}

group = "com.golfpvcc"
version = "0.0.1-SNAPSHOT"
description = "Version 2 of chirp"

dependencies {
    implementation(projects.user)
    implementation(projects.chat)
    implementation(projects.notification)
    implementation(projects.common)
    implementation(libs.kotlin.reflect)
    implementation(libs.spring.boot.starter.security)
    implementation(libs.spring.boot.starter.data.jpa)
    runtimeOnly(libs.postgresql)
}
