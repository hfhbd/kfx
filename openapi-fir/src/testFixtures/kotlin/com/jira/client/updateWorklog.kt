package com.jira.client

import com.jira.Worklog
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.parameter
import io.ktor.client.request.put
import io.ktor.client.request.setBody
import io.ktor.http.ContentType.Application.Json
import io.ktor.http.contentType
import kotlin.String
import kotlin.Unit

/**
 * Update a worklog entry
 * Updates an existing worklog entry. Note that:
 * - Fields possible for editing are: comment, visibility, started, timeSpent and timeSpentSeconds.
 * - Either timeSpent or timeSpentSeconds can be set.
 * - Fields which are not set will not be updated.
 * - For a request to be valid, it has to have at least one field change.
 * @param issueIdOrKey a string containing the issue id or key the worklog belongs to
 * @param id id of the worklog to be updated
 * @param newEstimate required when 'new' is selected for adjustEstimate
 * @param adjustEstimate allows you to provide specific instructions to update the remaining time estimate of the issue. Valid values are: new, leave, auto
 */
public suspend fun HttpClient.updateWorklog(
  input: Worklog,
  issueIdOrKey: String,
  id: String,
  newEstimate: String? = null,
  adjustEstimate: String? = null,
  builder: suspend HttpRequestBuilder.() -> Unit = {},
): Worklog {
  val response = put(urlString = """api/2/issue/${issueIdOrKey}/worklog/${id}""") {
    parameter("newEstimate", newEstimate)
    parameter("adjustEstimate", adjustEstimate)
    contentType(Json)
    setBody(body = input)
    builder()
  }
  val output = response.body<Worklog>()
  return output
}
