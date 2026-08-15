package com.jira.client

import com.jira.PrioritySchemeBean
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.`get`
import io.ktor.http.HttpStatusCode.Companion.NotFound
import kotlin.Long
import kotlin.Unit

/**
 * Get a priority scheme by ID
 * Gets a full representation of a priority scheme in JSON format.
 */
public suspend fun HttpClient.getPriorityScheme(schemeId: Long, builder: suspend HttpRequestBuilder.() -> Unit = {}): PrioritySchemeBean? {
  val response = `get`(urlString = """api/2/priorityschemes/${schemeId}""") {
    builder()
  }
  when {
    response.status == NotFound -> {
      return null
    }
  }
  val output = response.body<PrioritySchemeBean>()
  return output
}
