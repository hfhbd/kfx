package com.jira.client

import com.jira.ReindexBean
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.parameter
import io.ktor.client.request.post
import kotlin.Boolean
import kotlin.String
import kotlin.Unit
import kotlin.collections.List

/**
 * Reindex individual issues
 * Reindexes one or more individual issues. Indexing is performed synchronously - the call returns when indexing of the issues has completed or a failure occurs.
 */
public suspend fun HttpClient.reindexIssues(
  issueId: List<String>? = null,
  indexChangeHistory: Boolean? = false,
  indexWorklogs: Boolean? = false,
  indexComments: Boolean? = false,
  builder: suspend HttpRequestBuilder.() -> Unit = {},
): ReindexBean {
  val response = post(urlString = """api/2/reindex/issue""") {
    parameter("issueId", issueId)
    parameter("indexChangeHistory", indexChangeHistory)
    parameter("indexWorklogs", indexWorklogs)
    parameter("indexComments", indexComments)
    builder()
  }
  val output = response.body<ReindexBean>()
  return output
}
