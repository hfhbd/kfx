package com.jira.client

import com.jira.ProjectTypeBean
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.`get`
import kotlin.String
import kotlin.Unit

/**
 * Get project type by key
 * Returns the project type with the given key, if it is accessible to the logged in user. This takes into account whether the user is licensed on the Application that defines the project type.
 */
public suspend fun HttpClient.getAccessibleProjectTypeByKey(projectTypeKey: String, builder: suspend HttpRequestBuilder.() -> Unit = {}): ProjectTypeBean? {
  val response = `get`(urlString = """api/2/project/type/${projectTypeKey}/accessible""") {
    builder()
  }
  if (response.status.value == 404) {
    return null
  }
  val output = response.body<ProjectTypeBean>()
  return output
}
