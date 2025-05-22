package com.jira.client

import com.jira.Worklog
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.parameter
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType.Application.Json
import io.ktor.http.contentType
import kotlin.String
import kotlin.Unit

/**
 * Add a worklog entry
 * Adds a new worklog entry to an issue.
 * @param issueIdOrKey a string containing the issue id or key the worklog will be added to
 * @param newEstimate Required when 'new' is selected for adjustEstimate. e.g. "2d"
 * @param adjustEstimate Allows you to provide specific instructions to update the remaining time estimate of the issue. Valid values are: new, leave, manual, auto
 * @param reduceBy Required when 'manual' is selected for adjustEstimate. e.g. "2d"
 */
public suspend fun HttpClient.addWorklog(
  input: Worklog,
  issueIdOrKey: String,
  newEstimate: String? = null,
  adjustEstimate: String? = null,
  reduceBy: String? = null,
  builder: suspend HttpRequestBuilder.() -> Unit = {},
): Worklog {
  val response = post(urlString = """api/2/issue/${issueIdOrKey}/worklog""") {
    parameter("newEstimate", newEstimate)
    parameter("adjustEstimate", adjustEstimate)
    parameter("reduceBy", reduceBy)
    contentType(Json)
    setBody(body = input)
    builder()
  }
  val output = response.body<Worklog>()
  return output
}
