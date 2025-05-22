package com.jira.client

import io.ktor.client.HttpClient
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.delete
import io.ktor.client.request.parameter
import kotlin.Long
import kotlin.String
import kotlin.Unit

/**
 * Delete actors from project role
 * Deletes actors (users or groups) from a project role.
 */
public suspend fun HttpClient.deleteActor(
  projectIdOrKey: String,
  id: Long,
  user: String? = null,
  group: String? = null,
  builder: suspend HttpRequestBuilder.() -> Unit = {},
) {
  val response = delete(urlString = """api/2/project/${projectIdOrKey}/role/${id}""") {
    parameter("user", user)
    parameter("group", group)
    builder()
  }
}
