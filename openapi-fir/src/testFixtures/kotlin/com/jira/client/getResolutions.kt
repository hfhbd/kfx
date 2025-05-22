package com.jira.client

import com.jira.ResolutionJsonBean
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.`get`
import kotlin.Unit

/**
 * Get all resolutions
 * Returns a list of all resolutions.
 */
public suspend fun HttpClient.getResolutions(builder: suspend HttpRequestBuilder.() -> Unit = {}): ResolutionJsonBean {
  val response = `get`(urlString = """api/2/resolution""") {
    builder()
  }
  val output = response.body<ResolutionJsonBean>()
  return output
}
