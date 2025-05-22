package com.jira.client

import io.ktor.client.HttpClient
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.parameter
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType.Application.Json
import io.ktor.http.contentType
import kotlin.String
import kotlin.Unit

/**
 * Add a user as watcher
 * Adds a user to an issue's watcher list.
 * @param issueIdOrKey Issue id or key
 * @param userName The name of the user to add to the watcher list. If no name is specified, the current user is added.
 */
public suspend fun HttpClient.addWatcher_1(
  input: String,
  issueIdOrKey: String,
  userName: String? = null,
  builder: suspend HttpRequestBuilder.() -> Unit = {},
) {
  val response = post(urlString = """api/2/issue/${issueIdOrKey}/watchers""") {
    parameter("userName", userName)
    contentType(Json)
    setBody(body = input)
    builder()
  }
}
