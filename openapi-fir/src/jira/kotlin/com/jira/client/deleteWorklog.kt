package com.jira.client

import io.ktor.client.HttpClient
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.delete
import io.ktor.client.request.parameter
import kotlin.String
import kotlin.Unit

/**
 * Delete a worklog entry
 * Deletes an existing worklog entry.
 *
 * @param issueIdOrKey a string containing the issue id or key the worklog belongs to
 * @param id Id of the worklog to be deleted
 * @param newEstimate Required when 'new' is selected for adjustEstimate. e.g. "2d"
 * @param adjustEstimate Allows you to provide specific instructions to update the remaining time estimate of the issue. Valid values are: new, leave, manual, auto
 * @param increaseBy Required when 'manual' is selected for adjustEstimate. e.g. "2d"
 */
public suspend fun HttpClient.deleteWorklog(
  issueIdOrKey: String,
  id: String,
  newEstimate: String? = null,
  adjustEstimate: String? = null,
  increaseBy: String? = null,
  builder: suspend HttpRequestBuilder.() -> Unit = {},
) {
  val response = delete(urlString = """api/2/issue/${issueIdOrKey}/worklog/${id}""") {
    parameter("newEstimate", newEstimate)
    parameter("adjustEstimate", adjustEstimate)
    parameter("increaseBy", increaseBy)
    builder()
  }
}
