package blog.api.ktor

import blog.api.ktor.plugins.configureCors
import blog.api.ktor.plugins.configureRouting
import blog.api.ktor.plugins.configureSerialization
import blog.api.ktor.plugins.configureStatusPages
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.plugins.callloging.*
import io.ktor.server.request.*
import org.slf4j.event.Level

fun main(args: Array<String>): Unit = EngineMain.main(args)

fun Application.module() {
    install(CallLogging) {
        level = Level.INFO
        format { call ->
            val status = call.response.status()
            val httpMethod = call.request.httpMethod.value
            val userAgent = call.request.headers["User-Agent"]
            "Status: $status, Http method: $httpMethod, User Agent: $userAgent"
        }
        filter { call -> call.request.path().startsWith("/api/v1") }
    }
    configureRouting()
    configureSerialization()
    configureCors()
    configureStatusPages()

    (environment as ApplicationEngineEnvironment).connectors.forEach { connector ->
        println("Running application on http://:${connector.host}:${connector.port}")
    }
}
