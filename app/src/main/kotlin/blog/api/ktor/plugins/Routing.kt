package blog.api.ktor.plugins

import blog.api.ktor.routes.postRouting
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configureRouting() {
    routing {
        get("/") {
            call.respondText("Bad request")
        }
        get("/hello") {
            call.respondText("Hello World!")
        }

        postRouting()

        get("/internal-error") {
            throw Exception("Internal Server Error")
        }
        get("/authorization-error") {
            throw AuthorizationException("Forbidden Error")
        }
        get("/authentication-error") {
            call.respond(HttpStatusCode.Unauthorized)
        }
    }
}
