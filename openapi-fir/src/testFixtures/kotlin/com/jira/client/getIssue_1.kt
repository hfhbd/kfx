package com.jira.client

import com.jira.IssueBean
import com.jira.StringList
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.`get`
import io.ktor.client.request.parameter
import kotlin.Boolean
import kotlin.String
import kotlin.Unit
import kotlin.collections.List

/**
 * Get a single issue with Agile fields
 * Returns a single issue, for a given issue Id or issue key. Issues returned from this resource include Agile fields, like sprint, closedSprints, flagged, and epic.
 */
public suspend fun HttpClient.getIssue_1(
  issueIdOrKey: String,
  expand: String? = null,
  fields: List<StringList>? = null,
  updateHistory: Boolean? = null,
  builder: suspend HttpRequestBuilder.() -> Unit = {},
): IssueBean? {
  val response = `get`(urlString = """agile/1.0/issue/${issueIdOrKey}""") {
    parameter("expand", expand)
    parameter("fields", fields)
    parameter("updateHistory", updateHistory)
    builder()
  }
  if (response.status.value == 404) {
    return null
  }
  val output = response.body<IssueBean>()
  return output
}
