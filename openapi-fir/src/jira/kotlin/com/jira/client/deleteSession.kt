package com.jira.client

import io.ktor.client.HttpClient
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.delete
import kotlin.String
import kotlin.Unit

/**
 * Delete user session
 * Invalidates session of given user.
 *
 * @param username a String containing username.
 */
public suspend fun HttpClient.deleteSession(username: String, builder: suspend HttpRequestBuilder.() -> Unit = {}) {
  val response = delete(urlString = """api/2/user/session/${username}""") {
    builder()
  }
}
