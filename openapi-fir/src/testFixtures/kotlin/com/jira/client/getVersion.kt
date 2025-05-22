package com.jira.client

import com.jira.VersionBean
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.`get`
import io.ktor.client.request.parameter
import kotlin.String
import kotlin.Unit

/**
 * Get version details
 * Returns a version.
 */
public suspend fun HttpClient.getVersion(
  id: String,
  expand: String? = null,
  builder: suspend HttpRequestBuilder.() -> Unit = {},
): VersionBean? {
  val response = `get`(urlString = """api/2/version/${id}""") {
    parameter("expand", expand)
    builder()
  }
  if (response.status.value == 404) {
    return null
  }
  val output = response.body<VersionBean>()
  return output
}
