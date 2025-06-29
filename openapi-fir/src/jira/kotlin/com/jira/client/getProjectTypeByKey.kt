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
 * Returns the project type with the given key. In case of anonymous checks if they can access at least one project.
 */
public suspend fun HttpClient.getProjectTypeByKey(projectTypeKey: String, builder: suspend HttpRequestBuilder.() -> Unit = {}): ProjectTypeBean {
  val response = `get`(urlString = """api/2/project/type/${projectTypeKey}""") {
    builder()
  }
  val output = response.body<ProjectTypeBean>()
  return output
}
