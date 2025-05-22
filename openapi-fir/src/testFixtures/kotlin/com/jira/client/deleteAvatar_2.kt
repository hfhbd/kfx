package com.jira.client

import io.ktor.client.HttpClient
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.delete
import io.ktor.client.request.parameter
import kotlin.Long
import kotlin.String
import kotlin.Unit

/**
 * Delete avatar
 * Deletes avatar
 */
public suspend fun HttpClient.deleteAvatar_2(
  id: Long,
  username: String? = null,
  builder: suspend HttpRequestBuilder.() -> Unit = {},
) {
  val response = delete(urlString = """api/2/user/avatar/${id}""") {
    parameter("username", username)
    builder()
  }
}
