package com.jira.client

import com.jira.CurrentUser
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.`get`
import kotlin.Unit

/**
 * Get current user session information
 * Returns information about the currently authenticated user's session. If the caller is not authenticated they will get a 401 Unauthorized status code.
 */
public suspend fun HttpClient.currentUser(builder: suspend HttpRequestBuilder.() -> Unit = {}): CurrentUser {
  val response = `get`(urlString = """auth/1/session""") {
    builder()
  }
  val output = response.body<CurrentUser>()
  return output
}
