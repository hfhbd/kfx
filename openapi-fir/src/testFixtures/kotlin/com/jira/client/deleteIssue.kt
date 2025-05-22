package com.jira.client

import io.ktor.client.HttpClient
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.delete
import io.ktor.client.request.parameter
import kotlin.String
import kotlin.Unit

/**
 * Delete an issue
 * Deletes an issue. If the issue has subtasks you must set the parameter deleteSubtasks=true to delete the issue. You cannot delete an issue without its subtasks also being deleted.
 *
 * @param issueIdOrKey Issue id or key
 * @param deleteSubtasks A String of true or false indicating that any subtasks should also be deleted. If the issue has no subtasks this parameter is ignored. If the issue has subtasks and this parameter is missing or false, then the issue will not be deleted and an error will be returned.
 */
public suspend fun HttpClient.deleteIssue(
  issueIdOrKey: String,
  deleteSubtasks: String? = null,
  builder: suspend HttpRequestBuilder.() -> Unit = {},
) {
  val response = delete(urlString = """api/2/issue/${issueIdOrKey}""") {
    parameter("deleteSubtasks", deleteSubtasks)
    builder()
  }
}
