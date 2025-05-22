package com.jira.client

import com.jira.IssuesCreateResponse
import com.jira.IssuesUpdateBean
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType.Application.Json
import io.ktor.http.contentType
import kotlin.Unit

/**
 * Create an issue or sub-task from json - bulk operation.
 * Creates issues or sub-tasks from a JSON representation. Creates many issues in one bulk operation.
 * Creating a sub-task is similar to creating a regular issue. More details can be found in createIssue section.
 */
public suspend fun HttpClient.createIssues(input: IssuesUpdateBean, builder: suspend HttpRequestBuilder.() -> Unit = {}): IssuesCreateResponse {
  val response = post(urlString = """api/2/issue/bulk""") {
    contentType(Json)
    setBody(body = input)
    builder()
  }
  val output = response.body<IssuesCreateResponse>()
  return output
}
