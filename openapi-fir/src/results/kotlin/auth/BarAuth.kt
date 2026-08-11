package auth

import io.ktor.client.HttpClientConfig
import io.ktor.client.engine.HttpClientEngineConfig
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.request.`header`
import kotlin.String

public fun <T : HttpClientEngineConfig> HttpClientConfig<T>.BarAuth(apiKey: String) {
  defaultRequest {
    `header`("X-MY-API-KEY", apiKey)
  }
}
