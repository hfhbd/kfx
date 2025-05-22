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
 * Remove issues from any epic
 * Removes issues from epics. The user needs to have the edit issue permission for all issue they want to remove from epics. The maximum number of issues that can be moved in one operation is 50.
 */
public suspend fun HttpClient.removeIssuesFromEpic(input: IssueAssignRequestBean, builder: suspend HttpRequestBuilder.() -> Unit = {}) {
  val response = post(urlString = """agile/1.0/epic/none/issue""") {
    contentType(Json)
    setBody(body = input)
    builder()
  }
}
