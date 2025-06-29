package com.jira.client

import com.jira.IssueTypeWithStatusJsonBean
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.`get`
import kotlin.String
import kotlin.Unit

/**
 * Get all issue types with statuses for a project
 * Get all issue types with valid status values for a project
 */
public suspend fun HttpClient.getAllStatuses(projectIdOrKey: String, builder: suspend HttpRequestBuilder.() -> Unit = {}): IssueTypeWithStatusJsonBean? {
  val response = `get`(urlString = """api/2/project/${projectIdOrKey}/statuses""") {
    builder()
  }
  if (response.status.value == 404) {
    return null
  }
  val output = response.body<IssueTypeWithStatusJsonBean>()
  return output
}
