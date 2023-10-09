val ktor_version: String by project
val kotlin_version: String by project
val logback_version: String by project

plugins {
    // Apply the org.jetbrains.kotlin.jvm Plugin to add support for Kotlin.
    id("org.jetbrains.kotlin.jvm") version "1.9.10"

    // Apply the application plugin to add support for building a CLI application in Java.
    application

    // Add Ktor Gradle plugin
    id("io.ktor.plugin") version "2.3.5"
}

group = "blog.api.ktor"
version = "0.0.1"

repositories {
    // Use Maven Central for resolving dependencies.
    mavenCentral()
}

ktor {
    fatJar {
        archiveFileName.set("blog-api-ktor.jar")
    }
}

dependencies {
    // This dependency is used by the application.
    implementation("com.google.guava:guava:32.1.1-jre")

    // Use Kotlin JDK standard library
    implementation("org.jetbrains.kotlin:kotlin-stdlib")

    // Add Content Negotiation plugin
    implementation("io.ktor:ktor-server-content-negotiation:$ktor_version")

    // Add serialization
    implementation("io.ktor:ktor-serialization-kotlinx-json:$ktor_version")

    // Add Ktor core dependencies
    implementation("io.ktor:ktor-server-core:$ktor_version")
    implementation("io.ktor:ktor-server-netty:$ktor_version")

    // Add CORS
    implementation("io.ktor:ktor-server-cors:$ktor_version")

    // Add status pages
    implementation("io.ktor:ktor-server-status-pages:$ktor_version")
    
    // Add Swagger
    implementation("io.ktor:ktor-server-openapi")
    implementation("io.ktor:ktor-server-swagger")

    // Add logback
    implementation("ch.qos.logback:logback-classic:$logback_version")

    // Add logging
    implementation("io.ktor:ktor-server-call-logging:$kotlin_version")

    // Add YAML dependency to read YAML config
    implementation("io.ktor:ktor-server-config-yaml:$ktor_version")
    implementation("io.ktor:ktor-server-cors-jvm:2.3.5")

    // Unit tests
    testImplementation("io.ktor:ktor-server-tests:$ktor_version")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit:$kotlin_version")
}

// Apply a specific Java toolchain to ease working on different environments.
java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(17))
    }
}

application {
    // Define the main class for the application.
    mainClass.set("io.ktor.server.netty.EngineMain")

    val isDevelopment: Boolean = project.ext.has("development")
    applicationDefaultJvmArgs = listOf("-Dio.ktor.development=$isDevelopment")
}

