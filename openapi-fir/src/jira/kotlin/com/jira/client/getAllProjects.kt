package com.jira.client

import com.jira.ProjectBean
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.`get`
import io.ktor.client.request.parameter
import kotlin.Boolean
import kotlin.Int
import kotlin.String
import kotlin.Unit

/**
 * Get all visible projects
 * Returns all projects which are visible for the currently logged in user. If no user is logged in, it returns the list of projects that are visible when using anonymous access.
 */
public suspend fun HttpClient.getAllProjects(
  includeArchived: Boolean? = null,
  expand: String? = null,
  recent: Int? = null,
  browseArchive: Boolean? = null,
  builder: suspend HttpRequestBuilder.() -> Unit = {},
): ProjectBean {
  val response = `get`(urlString = """api/2/project""") {
    parameter("includeArchived", includeArchived)
    parameter("expand", expand)
    parameter("recent", recent)
    parameter("browseArchive", browseArchive)
    builder()
  }
  val output = response.body<ProjectBean>()
  return output
}
