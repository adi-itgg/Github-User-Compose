package me.syahdilla.putra.sholeh.githubusercompose.utils

import io.ktor.client.*
import io.ktor.client.engine.okhttp.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.resources.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json
import me.syahdilla.putra.sholeh.githubusercompose.BuildConfig

fun HttpResponse.isSuccess() = status.value in (200..302)

fun createDefaultHttpClient(isTestMode: Boolean = false): HttpClient {
    val logger = LogggerFactory("Network", isTestMode)
    return HttpClient(OkHttp) {
        defaultRequest {
            host = BuildConfig.API_HOST
            port = BuildConfig.API_PORT
            url { protocol = if (BuildConfig.API_PROTOCOL_ISHTTPS) URLProtocol.HTTPS else URLProtocol.HTTP }
        }
        install(Resources)
        install(ContentNegotiation) { json(Json { ignoreUnknownKeys = true }) }
        install("RequestLogging") {
            sendPipeline.intercept(HttpSendPipeline.Monitoring) {
                logger.debug("Request:", context.method.value, context.url.buildString())
            }
        }
        install(HttpRequestRetry) {
            maxRetries = 2
            retryIf { _, httpResponse -> !httpResponse.isSuccess() }
            delayMillis { retry -> retry * 5_000L }
        }
        HttpResponseValidator {
            validateResponse {
                logger.debug("Validator response code: ${it.status.value}")
            }
        }
    }
}