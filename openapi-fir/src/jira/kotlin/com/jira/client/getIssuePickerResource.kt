package com.jira.client

import com.jira.IssuePickerResult
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.`get`
import io.ktor.client.request.parameter
import kotlin.String
import kotlin.Unit

/**
 * Get suggested issues for auto-completion
 * Get issue picker resource
 *
 * @param currentProjectId the id of the project in context of which the request is executed
 * @param query the query
 * @param currentIssueKey the key of the issue in context of which the request is executed
 * @param showSubTasks if set to false, subtasks will not be included in the list
 * @param currentJQL the JQL in context of which the request is executed
 * @param showSubTaskParent if set to false and request is executed in context of a subtask, the parent issue will not be included in the auto-completion result, even if it matches the query
 */
public suspend fun HttpClient.getIssuePickerResource(
  currentProjectId: String? = null,
  query: String? = null,
  currentIssueKey: String? = null,
  showSubTasks: String? = null,
  currentJQL: String? = null,
  showSubTaskParent: String? = null,
  builder: suspend HttpRequestBuilder.() -> Unit = {},
): IssuePickerResult {
  val response = `get`(urlString = """api/2/issue/picker""") {
    parameter("currentProjectId", currentProjectId)
    parameter("query", query)
    parameter("currentIssueKey", currentIssueKey)
    parameter("showSubTasks", showSubTasks)
    parameter("currentJQL", currentJQL)
    parameter("showSubTaskParent", showSubTaskParent)
    builder()
  }
  val output = response.body<IssuePickerResult>()
  return output
}
