package com.jira.client

import com.jira.IssueTypeUpdateBean
import io.ktor.client.HttpClient
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.put
import io.ktor.client.request.setBody
import io.ktor.http.ContentType.Application.Json
import io.ktor.http.contentType
import kotlin.String
import kotlin.Unit

/**
 * Update specified issue type from JSON representation
 * Updates the specified issue type from a JSON representation.
 * @param id The issue type id.
 */
public suspend fun HttpClient.updateIssueType(
  input: IssueTypeUpdateBean,
  id: String,
  builder: suspend HttpRequestBuilder.() -> Unit = {},
) {
  val response = put(urlString = """api/2/issuetype/${id}""") {
    contentType(Json)
    setBody(body = input)
    builder()
  }
}
