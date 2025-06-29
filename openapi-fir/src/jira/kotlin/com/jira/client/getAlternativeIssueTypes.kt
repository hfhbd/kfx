package com.jira.client

import com.jira.IssueTypeJsonBean
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.`get`
import kotlin.String
import kotlin.Unit

/**
 * Get list of alternative issue types for given id
 * Returns a list of all alternative issue types for the given issue type id.
 *
 * @param id The issue type id.
 */
public suspend fun HttpClient.getAlternativeIssueTypes(id: String, builder: suspend HttpRequestBuilder.() -> Unit = {}): IssueTypeJsonBean? {
  val response = `get`(urlString = """api/2/issuetype/${id}/alternatives""") {
    builder()
  }
  if (response.status.value == 404) {
    return null
  }
  val output = response.body<IssueTypeJsonBean>()
  return output
}
