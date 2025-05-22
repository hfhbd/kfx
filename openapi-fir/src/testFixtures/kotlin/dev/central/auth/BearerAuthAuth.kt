package dev.central.auth

import io.ktor.client.HttpClientConfig
import io.ktor.client.engine.HttpClientEngineConfig
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.request.bearerAuth
import kotlin.String

public fun <T : HttpClientEngineConfig> HttpClientConfig<T>.BearerAuthAuth(token: String) {
  defaultRequest {
    bearerAuth(token)
  }
}
