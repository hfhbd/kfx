package com.jira.client

import com.jira.IssueCreateResponse
import com.jira.IssueUpdateBean
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.parameter
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType.Application.Json
import io.ktor.http.contentType
import kotlin.Boolean
import kotlin.Unit

/**
 * Create an issue or sub-task from json
 * Creates an issue or a sub-task from a JSON representation.
 * The fields that can be set on create, in either the fields parameter or the update parameter can be determined using the /rest/api/2/issue/createmeta resource.
 * If a field is not configured to appear on the create screen, then it will not be in the createmeta, and a field
 * validation error will occur if it is submitted.
 * Creating a sub-task is similar to creating a regular issue, with two important differences:
 * - the issueType field must correspond to a sub-task issue type (you can use /issue/createmeta to discover sub-task issue types), and
 * - you must provide a parent field in the issue create request containing the id or key of the parent issue.
 * The updateHistory param adds the project that this issue is created in, to the current user's project history, if set to true (by default, the project history is not updated).
 * You can view the project history in the Jira application, via the Projects dropdown.
 */
public suspend fun HttpClient.createIssue(
  input: IssueUpdateBean,
  updateHistory: Boolean? = false,
  builder: suspend HttpRequestBuilder.() -> Unit = {},
): IssueCreateResponse {
  val response = post(urlString = """api/2/issue""") {
    parameter("updateHistory", updateHistory)
    contentType(Json)
    setBody(body = input)
    builder()
  }
  val output = response.body<IssueCreateResponse>()
  return output
}
