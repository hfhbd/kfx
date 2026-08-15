package com.jira.client

import com.jira.WorklogResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.`get`
import io.ktor.http.HttpStatusCode.Companion.NotFound
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
): WorklogResponse? {
  val response = `get`(urlString = """api/2/issue/${issueIdOrKey}/worklog/${id}""") {
    builder()
  }
  when {
    response.status == NotFound -> {
      return null
    }
  }
  val output = response.body<WorklogResponse>()
  return output
}
