package com.jira.client

import io.ktor.client.HttpClient
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.delete
import kotlin.String
import kotlin.Unit

/**
 * Delete specified issue type scheme
 * Deletes the specified issue type scheme. Any projects associated with this IssueTypeScheme will be automatically associated with the global default IssueTypeScheme.
 *
 * @param schemeId The id of the issue type scheme to remove.
 */
public suspend fun HttpClient.deleteIssueTypeScheme(schemeId: String, builder: suspend HttpRequestBuilder.() -> Unit = {}) {
  val response = delete(urlString = """api/2/issuetypescheme/${schemeId}""") {
    builder()
  }
}
