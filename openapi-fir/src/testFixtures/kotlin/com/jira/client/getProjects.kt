package com.jira.client

import com.jira.ProjectJsonBean
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.`get`
import io.ktor.client.request.parameter
import kotlin.Int
import kotlin.Long
import kotlin.Unit

/**
 * Get all projects associated with the board
 * Returns all projects that are associated with the board, for the given board Id. A project is associated with a board only if the board filter explicitly filters issues by the project and guaranties that all issues will come for one of those projects e.g. board's filter with "project in (PR-1, PR-1) OR reporter = admin" jql Projects are returned only if user can browse all projects that are associated with the board. Note, if the user does not have permission to view the board, no projects will be returned at all. Returned projects are ordered by the name.
 */
public suspend fun HttpClient.getProjects(
  boardId: Long,
  maxResults: Int? = null,
  startAt: Long? = null,
  builder: suspend HttpRequestBuilder.() -> Unit = {},
): ProjectJsonBean? {
  val response = `get`(urlString = """agile/1.0/board/${boardId}/project""") {
    parameter("maxResults", maxResults)
    parameter("startAt", startAt)
    builder()
  }
  if (response.status.value == 404) {
    return null
  }
  val output = response.body<ProjectJsonBean>()
  return output
}
