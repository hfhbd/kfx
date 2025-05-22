package com.jira.client

import com.jira.ResolutionJsonBean
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.`get`
import kotlin.String
import kotlin.Unit

/**
 * Get a resolution by ID
 * Returns a resolution.
 */
public suspend fun HttpClient.getResolution(id: String, builder: suspend HttpRequestBuilder.() -> Unit = {}): ResolutionJsonBean? {
  val response = `get`(urlString = """api/2/resolution/${id}""") {
    builder()
  }
  if (response.status.value == 404) {
    return null
  }
  val output = response.body<ResolutionJsonBean>()
  return output
}
