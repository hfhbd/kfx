package com.jira.client

import com.jira.ProjectBean
import com.jira.ProjectUpdateBean
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.parameter
import io.ktor.client.request.put
import io.ktor.client.request.setBody
import io.ktor.http.ContentType.Application.Json
import io.ktor.http.contentType
import kotlin.String
import kotlin.Unit

/**
 * Update a project
 * Updates a project. Only non null values sent in JSON will be updated in the project. Values available for the assigneeType field are: "PROJECT_LEAD" and "UNASSIGNED".
 */
public suspend fun HttpClient.updateProject(
  input: ProjectUpdateBean,
  projectIdOrKey: String,
  expand: String? = null,
  builder: suspend HttpRequestBuilder.() -> Unit = {},
): ProjectBean? {
  val response = put(urlString = """api/2/project/${projectIdOrKey}""") {
    parameter("expand", expand)
    contentType(Json)
    setBody(body = input)
    builder()
  }
  if (response.status.value == 404) {
    return null
  }
  val output = response.body<ProjectBean>()
  return output
}
