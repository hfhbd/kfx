package com.jira.client

import com.jira.ProjectBean
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.put
import io.ktor.http.HttpStatusCode.Companion.NotFound
import kotlin.String
import kotlin.Unit

/**
 * Update project type
 * Updates the type of a project
 */
public suspend fun HttpClient.updateProjectType(
  projectIdOrKey: String,
  newProjectTypeKey: String,
  builder: suspend HttpRequestBuilder.() -> Unit = {},
): ProjectBean? {
  val response = put(urlString = """api/2/project/${projectIdOrKey}/type/${newProjectTypeKey}""") {
    builder()
  }
  when {
    response.status == NotFound -> {
      return null
    }
  }
  val output = response.body<ProjectBean>()
  return output
}
