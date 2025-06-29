package com.jira.client

import io.ktor.client.HttpClient
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.delete
import kotlin.Long
import kotlin.Unit

/**
 * Delete a priority scheme
 * Deletes a priority scheme. All projects using deleted scheme will use default priority scheme afterwards.
 */
public suspend fun HttpClient.deletePriorityScheme(schemeId: Long, builder: suspend HttpRequestBuilder.() -> Unit = {}) {
  val response = delete(urlString = """api/2/priorityschemes/${schemeId}""") {
    builder()
  }
}
