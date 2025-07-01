package com.jira.client

import io.ktor.client.HttpClient
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.delete
import kotlin.Unit

/**
 * Delete current user session
 * Logs the current user out of Jira, destroying the existing session, if any.
 */
public suspend fun HttpClient.logout(builder: suspend HttpRequestBuilder.() -> Unit = {}) {
  val response = delete(urlString = """auth/1/session""") {
    builder()
  }
}
