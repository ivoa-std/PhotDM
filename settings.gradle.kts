
rootProject.name = "PhotDM"


pluginManagement {
    repositories {
        mavenLocal() // IMPL allow picking up from local - should be removed when publishing
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    repositories {
        mavenLocal()
        mavenCentral()
    }
}