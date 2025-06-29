package com.jira.client

import com.jira.CreateMetaIssueTypeBean
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.`get`
import io.ktor.client.request.parameter
import kotlin.String
import kotlin.Unit

/**
 * Get metadata for project issue types
 * Returns the metadata for issue types used for creating issues. Data will not be returned if the user does not have permission to create issues in that project.
 *
 * @param projectIdOrKey Project id or key
 * @param maxResults How many results on the page should be included
 * @param startAt The page offset
 */
public suspend fun HttpClient.getCreateIssueMetaProjectIssueTypes(
  projectIdOrKey: String,
  maxResults: String? = null,
  startAt: String? = null,
  builder: suspend HttpRequestBuilder.() -> Unit = {},
): CreateMetaIssueTypeBean {
  val response = `get`(urlString = """api/2/issue/createmeta/${projectIdOrKey}/issuetypes""") {
    parameter("maxResults", maxResults)
    parameter("startAt", startAt)
    builder()
  }
  val output = response.body<CreateMetaIssueTypeBean>()
  return output
}
