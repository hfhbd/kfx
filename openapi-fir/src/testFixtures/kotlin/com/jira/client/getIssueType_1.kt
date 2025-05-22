package com.jira.client

import com.jira.IssueTypeJsonBean
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.`get`
import kotlin.String
import kotlin.Unit

/**
 * Get full representation of issue type by id
 * Returns a full representation of the issue type that has the given id.
 *
 * @param id The issue type id.
 */
public suspend fun HttpClient.getIssueType_1(id: String, builder: suspend HttpRequestBuilder.() -> Unit = {}): IssueTypeJsonBean? {
  val response = `get`(urlString = """api/2/issuetype/${id}""") {
    builder()
  }
  if (response.status.value == 404) {
    return null
  }
  val output = response.body<IssueTypeJsonBean>()
  return output
}
