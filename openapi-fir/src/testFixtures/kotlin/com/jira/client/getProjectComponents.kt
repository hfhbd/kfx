package com.jira.client

import com.jira.ComponentBean
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.`get`
import kotlin.String
import kotlin.Unit

/**
 * Get project components
 * Contains a full representation of the specified project's components.
 */
public suspend fun HttpClient.getProjectComponents(projectIdOrKey: String, builder: suspend HttpRequestBuilder.() -> Unit = {}): ComponentBean? {
  val response = `get`(urlString = """api/2/project/${projectIdOrKey}/components""") {
    builder()
  }
  if (response.status.value == 404) {
    return null
  }
  val output = response.body<ComponentBean>()
  return output
}
