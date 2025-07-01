package com.jira.auth

import io.ktor.client.HttpClientConfig
import io.ktor.client.engine.HttpClientEngineConfig
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.request.basicAuth
import kotlin.String

public fun <T : HttpClientEngineConfig> HttpClientConfig<T>.basicAuth(userName: String, password: String) {
  defaultRequest {
    basicAuth(userName, password)
  }
}
