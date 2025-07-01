package com.jira.client

import com.jira.WorklogWithPaginationBean
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.`get`
import kotlin.String
import kotlin.Unit

/**
 * Get worklogs for an issue
 * Returns all work logs for an issue. Work logs won't be returned if the Log work field is hidden for the project.
 *
 * @param issueIdOrKey Issue id or key
 */
public suspend fun HttpClient.getIssueWorklog(issueIdOrKey: String, builder: suspend HttpRequestBuilder.() -> Unit = {}): WorklogWithPaginationBean? {
  val response = `get`(urlString = """api/2/issue/${issueIdOrKey}/worklog""") {
    builder()
  }
  if (response.status.value == 404) {
    return null
  }
  val output = response.body<WorklogWithPaginationBean>()
  return output
}
