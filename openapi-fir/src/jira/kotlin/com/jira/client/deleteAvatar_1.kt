package com.jira.client

import io.ktor.client.HttpClient
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.delete
import kotlin.Long
import kotlin.String
import kotlin.Unit

/**
 * Delete avatar by ID
 * Deletes avatar
 */
public suspend fun HttpClient.deleteAvatar_1(
  id: Long,
  type: String,
  owningObjectId: String,
  builder: suspend HttpRequestBuilder.() -> Unit = {},
) {
  val response = delete(urlString = """api/2/universal_avatar/type/${type}/owner/${owningObjectId}/avatar/${id}""") {
    builder()
  }
}
