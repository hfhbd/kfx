package com.jira.client

import com.jira.Worklog
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.`get`
import kotlin.String
import kotlin.Unit

/**
 * Get a worklog by id
 * Returns a specific worklog. The work log won't be returned if the Log work field is hidden for the project.
 *
 * @param issueIdOrKey Issue id or key
 * @param id Worklog id
 */
public suspend fun HttpClient.getWorklog(
  issueIdOrKey: String,
  id: String,
  builder: suspend HttpRequestBuilder.() -> Unit = {},
): Worklog? {
  val response = `get`(urlString = """api/2/issue/${issueIdOrKey}/worklog/${id}""") {
    builder()
  }
  if (response.status.value == 404) {
    return null
  }
  val output = response.body<Worklog>()
  return output
}
