package com.jira.client

import com.jira.LinkIssueRequestJsonBean
import io.ktor.client.HttpClient
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType.Application.Json
import io.ktor.http.contentType
import kotlin.Unit

/**
 * Create an issue link between two issues
 * Creates an issue link between two issues.
 */
public suspend fun HttpClient.linkIssues(input: LinkIssueRequestJsonBean, builder: suspend HttpRequestBuilder.() -> Unit = {}) {
  val response = post(urlString = """api/2/issueLink""") {
    contentType(Json)
    setBody(body = input)
    builder()
  }
}
