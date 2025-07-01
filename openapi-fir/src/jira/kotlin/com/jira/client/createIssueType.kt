package com.jira.client

import com.jira.IssueTypeCreateBean
import io.ktor.client.HttpClient
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType.Application.Json
import io.ktor.http.contentType
import kotlin.Unit

/**
 * Create an issue type from JSON representation
 * Creates an issue type from a JSON representation and adds the issue newly created issue type to the default issue type scheme.
 */
public suspend fun HttpClient.createIssueType(input: IssueTypeCreateBean, builder: suspend HttpRequestBuilder.() -> Unit = {}) {
  val response = post(urlString = """api/2/issuetype""") {
    contentType(Json)
    setBody(body = input)
    builder()
  }
}
