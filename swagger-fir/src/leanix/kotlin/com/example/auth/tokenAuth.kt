package com.example.auth

import io.github.hfhbd.kfx.oauth2.OAuth2Token
import io.ktor.client.HttpClientConfig
import io.ktor.client.call.body
import io.ktor.client.engine.HttpClientEngineConfig
import io.ktor.client.plugins.auth.Auth
import io.ktor.client.plugins.auth.providers.BearerTokens
import io.ktor.client.plugins.auth.providers.bearer
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.basicAuth
import io.ktor.client.request.parameter
import io.ktor.client.request.post
import io.ktor.http.ContentType.Application.FormUrlEncoded
import io.ktor.http.contentType
import kotlin.String
import kotlin.Unit

public fun <T : HttpClientEngineConfig> HttpClientConfig<T>.tokenAuth(
  clientId: String,
  clientSecret: String,
  builder: suspend HttpRequestBuilder.() -> Unit = {},
) {
  Auth {
    bearer {
      refreshTokens {
        val response = client.post(urlString = """services/mtm/v1/oauth2/token""") {
          parameter("grant_type", "client_credentials")
          basicAuth(clientId, clientSecret)
          contentType(FormUrlEncoded)
          markAsRefreshTokenRequest()
          builder()
        }
        val output = response.body<OAuth2Token>()
        BearerTokens(output.accessToken, output.refreshToken ?: "")
      }
    }
  }
}
