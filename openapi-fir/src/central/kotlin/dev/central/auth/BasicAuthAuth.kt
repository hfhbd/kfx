package dev.central.auth

import io.ktor.client.HttpClientConfig
import io.ktor.client.engine.HttpClientEngineConfig
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.request.basicAuth
import kotlin.String

public fun <T : HttpClientEngineConfig> HttpClientConfig<T>.BasicAuthAuth(userName: String, password: String) {
  defaultRequest {
    basicAuth(userName, password)
  }
}
