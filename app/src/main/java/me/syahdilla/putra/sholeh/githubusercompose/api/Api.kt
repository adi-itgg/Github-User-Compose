package me.syahdilla.putra.sholeh.githubusercompose.api

import io.ktor.resources.*
import kotlinx.serialization.Serializable

class Api {
    @Serializable
    @Resource("/search/users")
    class Search(val q: String)

    @Serializable
    @Resource("/users/{user}")
    class User(val user: String)
}