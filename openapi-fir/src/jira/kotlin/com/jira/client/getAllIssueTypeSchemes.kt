package com.jira.client

import com.jira.IssueTypeSchemeListBean
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.`get`
import kotlin.Unit

/**
 * Get list of all issue type schemes visible to user
 * Returns a list of all issue type schemes visible to the user. All issue types associated with the scheme will only be returned if an additional query parameter is provided: expand=schemes.issueTypes. Similarly, the default issue type associated with the scheme (if one exists) will only be returned if an additional query parameter is provided: expand=schemes.defaultIssueType. Note that both query parameters can be used together: expand=schemes.issueTypes,schemes.defaultIssueType.
 */
public suspend fun HttpClient.getAllIssueTypeSchemes(builder: suspend HttpRequestBuilder.() -> Unit = {}): IssueTypeSchemeListBean {
  val response = `get`(urlString = """api/2/issuetypescheme""") {
    builder()
  }
  val output = response.body<IssueTypeSchemeListBean>()
  return output
}
