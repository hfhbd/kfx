package com.jira.client

import com.jira.IssueAssignRequestBean
import io.ktor.client.HttpClient
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType.Application.Json
import io.ktor.http.contentType
import kotlin.Long
import kotlin.Unit

/**
 * Move issues to a sprint
 * Moves issues to a sprint, for a given sprint Id. Issues can only be moved to open or active sprints. The maximum number of issues that can be moved in one operation is 50.
 */
public suspend fun HttpClient.moveIssuesToSprint(
  input: IssueAssignRequestBean,
  sprintId: Long,
  builder: suspend HttpRequestBuilder.() -> Unit = {},
) {
  val response = post(urlString = """agile/1.0/sprint/${sprintId}/issue""") {
    contentType(Json)
    setBody(body = input)
    builder()
  }
}
