package com.jira.client

import com.jira.IssueRefJsonBean
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.`get`
import kotlin.String
import kotlin.Unit

/**
 * Get an issue's subtask list
 * Returns an issue's subtask list
 *
 * @param issueIdOrKey The parent issue's key or id
 */
public suspend fun HttpClient.getSubTasks(issueIdOrKey: String, builder: suspend HttpRequestBuilder.() -> Unit = {}): IssueRefJsonBean? {
  val response = `get`(urlString = """api/2/issue/${issueIdOrKey}/subtask""") {
    builder()
  }
  if (response.status.value == 404) {
    return null
  }
  val output = response.body<IssueRefJsonBean>()
  return output
}
