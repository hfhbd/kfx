package com.jira.client

import com.jira.IssueTypeJsonBean
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.`get`
import kotlin.Unit

/**
 * Get list of all issue types visible to user
 * Returns a list of all issue types visible to the user
 */
public suspend fun HttpClient.getIssueAllTypes(builder: suspend HttpRequestBuilder.() -> Unit = {}): IssueTypeJsonBean {
  val response = `get`(urlString = """api/2/issuetype""") {
    builder()
  }
  val output = response.body<IssueTypeJsonBean>()
  return output
}
