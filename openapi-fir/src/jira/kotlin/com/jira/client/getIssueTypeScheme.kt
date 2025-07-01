package com.jira.client

import com.jira.IssueTypeSchemeBean
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.`get`
import kotlin.String
import kotlin.Unit

/**
 * Get full representation of issue type scheme by id
 * Returns a full representation of the issue type scheme that has the given id
 *
 * @param schemeId A String containing an issue type scheme's id.
 */
public suspend fun HttpClient.getIssueTypeScheme(schemeId: String, builder: suspend HttpRequestBuilder.() -> Unit = {}): IssueTypeSchemeBean? {
  val response = `get`(urlString = """api/2/issuetypescheme/${schemeId}""") {
    builder()
  }
  if (response.status.value == 404) {
    return null
  }
  val output = response.body<IssueTypeSchemeBean>()
  return output
}
