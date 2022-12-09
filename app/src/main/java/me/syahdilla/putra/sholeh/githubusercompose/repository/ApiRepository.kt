package me.syahdilla.putra.sholeh.githubusercompose.repository

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.plugins.resources.*
import io.ktor.client.request.*
import me.syahdilla.putra.sholeh.githubusercompose.api.Api
import me.syahdilla.putra.sholeh.githubusercompose.data.SearchResult
import me.syahdilla.putra.sholeh.githubusercompose.data.User
import me.syahdilla.putra.sholeh.githubusercompose.data.UserDetails
import me.syahdilla.putra.sholeh.githubusercompose.utils.loggerFactory
import me.syahdilla.putra.sholeh.githubusercompose.utils.tryRun

class ApiRepository(
    private val client: HttpClient,
    isTestMode: Boolean = false
) {

    private val logger by loggerFactory(isTestMode = isTestMode)

    suspend fun searchUser(query: String): SearchResult =
        tryRun {
            logger.debug { "Search user with query $query" }
            client.get(Api.Search(query)).body<SearchResult>()
        }.onFailure {
            logger.error { "Failure find user with query $query" }
            logger.error { "Error details: ${it.stackTraceToString()}" }
        }.getOrDefault(SearchResult())

    suspend fun getUserDetails(user: User) =
        tryRun {
            logger.debug { "Get details user: ${user.name}" }
            client.get { url(user.url) }.body<UserDetails>()
        }.onFailure {
            logger.error { "Failure get details user ${user.name}" }
            logger.error { "Error details: ${it.stackTraceToString()}" }
        }

}