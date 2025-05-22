package com.jira.client

import com.jira.IssueAssignRequestBean
import io.ktor.client.HttpClient
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType.Application.Json
import io.ktor.http.contentType
import kotlin.String
import kotlin.Unit

/**
 * Move issues to a specific epic
 * Moves issues to an epic, for a given epic id. Issues can be only in a single epic at the same time. That means that already assigned issues to an epic, will not be assigned to the previous epic anymore. The user needs to have the edit issue permission for all issue they want to move and to the epic. The maximum number of issues that can be moved in one operation is 50.
 */
public suspend fun HttpClient.moveIssuesToEpic(
  input: IssueAssignRequestBean,
  epicIdOrKey: String,
  builder: suspend HttpRequestBuilder.() -> Unit = {},
) {
  val response = post(urlString = """agile/1.0/epic/${epicIdOrKey}/issue""") {
    contentType(Json)
    setBody(body = input)
    builder()
  }
}
