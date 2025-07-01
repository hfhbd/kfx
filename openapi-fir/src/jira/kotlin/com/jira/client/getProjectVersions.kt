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
 * Get project versions
 * Contains a full representation of a the specified project's versions.
 */
public suspend fun HttpClient.getProjectVersions(
  projectIdOrKey: String,
  expand: String? = null,
  builder: suspend HttpRequestBuilder.() -> Unit = {},
): VersionBean? {
  val response = `get`(urlString = """api/2/project/${projectIdOrKey}/versions""") {
    parameter("expand", expand)
    builder()
  }
  if (response.status.value == 404) {
    return null
  }
  val output = response.body<VersionBean>()
  return output
}
