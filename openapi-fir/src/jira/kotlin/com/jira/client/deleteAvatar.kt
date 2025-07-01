package com.jira.client

import io.ktor.client.HttpClient
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.delete
import kotlin.Long
import kotlin.String
import kotlin.Unit

/**
 * Delete an avatar
 * Deletes avatar
 */
public suspend fun HttpClient.deleteAvatar(
  projectIdOrKey: String,
  id: Long,
  builder: suspend HttpRequestBuilder.() -> Unit = {},
) {
  val response = delete(urlString = """api/2/project/${projectIdOrKey}/avatar/${id}""") {
    builder()
  }
}
