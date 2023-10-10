package blog.api.ktor

import blog.api.ktor.plugins.*
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*

fun main(args: Array<String>): Unit = EngineMain.main(args)

fun Application.module() {
    configureSerialization()
    configureCors()
    configureRouting()
    configureStatusPages()
    configureLogging()

    (environment as ApplicationEngineEnvironment).connectors.forEach { connector ->
        println("Running application on http://:${connector.host}:${connector.port}")
    }
}
