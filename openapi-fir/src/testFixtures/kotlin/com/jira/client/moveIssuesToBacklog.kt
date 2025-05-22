package com.jira.client

import com.jira.IssueAssignRequestBean
import io.ktor.client.HttpClient
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType.Application.Json
import io.ktor.http.contentType
import kotlin.Unit

/**
 * Update issues to move them to the backlog
 * Move issues to the backlog. This operation is equivalent to remove future and active sprints from a given set of issues. At most 50 issues may be moved at once.
 */
public suspend fun HttpClient.moveIssuesToBacklog(input: IssueAssignRequestBean, builder: suspend HttpRequestBuilder.() -> Unit = {}) {
  val response = post(urlString = """agile/1.0/backlog/issue""") {
    contentType(Json)
    setBody(body = input)
    builder()
  }
}
