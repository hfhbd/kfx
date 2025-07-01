package com.jira.client

import com.jira.FieldMetaBean
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.`get`
import io.ktor.client.request.parameter
import kotlin.String
import kotlin.Unit

/**
 * Get metadata for issue types used for creating issues
 * Returns the metadata for issue types used for creating issues. Data will not be returned if the user does not have permission to create issues in that project.
 *
 * @param issueTypeId Issue type id
 * @param projectIdOrKey Project id or key
 * @param maxResults How many results on the page should be included
 * @param startAt The page offset
 */
public suspend fun HttpClient.getCreateIssueMetaFields(
  issueTypeId: String,
  projectIdOrKey: String,
  maxResults: String? = null,
  startAt: String? = null,
  builder: suspend HttpRequestBuilder.() -> Unit = {},
): FieldMetaBean {
  val response = `get`(urlString = """api/2/issue/createmeta/${projectIdOrKey}/issuetypes/${issueTypeId}""") {
    parameter("maxResults", maxResults)
    parameter("startAt", startAt)
    builder()
  }
  val output = response.body<FieldMetaBean>()
  return output
}
