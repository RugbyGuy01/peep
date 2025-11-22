pluginManagement {
    includeBuild("build-logic")
    repositories {
        maven { url = uri("https://repo.spring.io/milestone") }  // vpg 11/221/2025
        maven { url = uri("https://repo.spring.io/snapshot") }
        gradlePluginPortal()
    }
}
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")
rootProject.name = "peep"

include("app")
include("user")
include("chat")
include("notification")
include("common")