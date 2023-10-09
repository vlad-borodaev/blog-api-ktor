val ktorVersion: String by project
val kotlinVersion: String by project
val logbackVersion: String by project

plugins {
    // Apply the org.jetbrains.kotlin.jvm Plugin to add support for Kotlin.
    id("org.jetbrains.kotlin.jvm") version "1.9.10"

    // Apply the application plugin to add support for building a CLI application in Java.
    application

    // Add Ktor Gradle plugin
    id("io.ktor.plugin") version "2.3.5"

    kotlin("plugin.serialization") version "1.9.0"
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
    implementation("io.ktor:ktor-server-content-negotiation:$ktorVersion")

    // Add serialization
    implementation("io.ktor:ktor-serialization-kotlinx-json:$ktorVersion")

    // Add Ktor core dependencies
    implementation("io.ktor:ktor-server-core:$ktorVersion")
    implementation("io.ktor:ktor-server-netty:$ktorVersion")

    // Add CORS
    implementation("io.ktor:ktor-server-cors:$ktorVersion")

    // Add status pages
    implementation("io.ktor:ktor-server-status-pages:$ktorVersion")
    
    // Add Swagger
    implementation("io.ktor:ktor-server-openapi:$ktorVersion")
    implementation("io.ktor:ktor-server-swagger:$ktorVersion")

    // Add logback
    implementation("ch.qos.logback:logback-classic:$logbackVersion")

    // Add logging
    implementation("io.ktor:ktor-server-call-logging:$kotlinVersion")

    // Add YAML dependency to read YAML config
    implementation("io.ktor:ktor-server-config-yaml:$ktorVersion")
    implementation("io.ktor:ktor-server-cors-jvm:2.3.5")

    // Unit tests
    testImplementation("io.ktor:ktor-server-tests:$ktorVersion")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit:$kotlinVersion")
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

