package blog.api.ktor.models

import kotlinx.serialization.Serializable

@Serializable
data class Post(val id: String,
                val title: String,
                val description: String,
                val rating: Int)

// @FIXME: a tmp solution, later on need to store the data in the DB
val postStorage = mutableListOf<Post>()