package com.jira.client

import com.jira.ProjectTypeBean
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.`get`
import kotlin.Unit

/**
 * Get all project types
 * Returns all the project types defined on the Jira instance, not taking into account whether the license to use those project types is valid or not. In case of anonymous checks if they can access at least one project.
 */
public suspend fun HttpClient.getAllProjectTypes(builder: suspend HttpRequestBuilder.() -> Unit = {}): ProjectTypeBean {
  val response = `get`(urlString = """api/2/project/type""") {
    builder()
  }
  val output = response.body<ProjectTypeBean>()
  return output
}
