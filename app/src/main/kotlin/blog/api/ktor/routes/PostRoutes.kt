package blog.api.ktor.routes

import blog.api.ktor.models.Post
import blog.api.ktor.models.postStorage
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.postRouting() {
    route("/post") {
        get {
            if (postStorage.isEmpty()) {
                call.respond(postStorage)
            } else {
                call.respondText("No posts found", status = HttpStatusCode.NotFound)
            }
        }

        get("{id}?") {
            val id = call.parameters["id"] ?: return@get call.respondText(
                "Missing ID",
                status = HttpStatusCode.BadRequest
            )
            val post = postStorage.find { it.id == id } ?: return@get call.respondText(
                "No post with id $id",
                status = HttpStatusCode.NotFound
            )
            call.respond(post)
        }

        post {
            val post = call.receive<Post>()
            postStorage.add(post)
            call.respondText("Post successfully created", status = HttpStatusCode.Created)
        }

        delete("{id}?") {
            val id = call.parameters["id"] ?: return@delete call.respondText(
                "Missing ID",
                status = HttpStatusCode.BadRequest
            )
            if (postStorage.removeIf { it.id == id }) {
                call.respondText("Post successfully removed", status = HttpStatusCode.OK)
            } else {
                call.respondText("No posts found", status = HttpStatusCode.NotFound)
            }
        }
    }
}