plugins {
    id("java-library")
    id("peep.spring-boot-service")
    kotlin("plugin.jpa")
}

group = "com.golfpvcc"
version = "unspecified"

repositories {
    mavenCentral()
    maven { url = uri("https://repo.spring.io/milestone") }
    maven { url = uri("https://repo.spring.io/snapshot") }
}

dependencies {
    implementation(projects.common)

    implementation(libs.spring.boot.starter.mail)
    implementation(libs.spring.boot.starter.thymeleaf)
    implementation(libs.spring.boot.starter.amqp)

    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}
// kotlin {     vpg 11/21/2025
//    jvmToolchain(21)
//}