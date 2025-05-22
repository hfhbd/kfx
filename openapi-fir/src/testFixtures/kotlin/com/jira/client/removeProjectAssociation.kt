package com.jira.client

import io.ktor.client.HttpClient
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.delete
import kotlin.String
import kotlin.Unit

/**
 * Remove given project association for specified scheme
 * For the specified issue type scheme, removes the given project association
 *
 * @param projIdOrKey The id or key of the project that is to be un-associated with the issue type scheme
 * @param schemeId The id of the issue type scheme whose project association we're removing
 */
public suspend fun HttpClient.removeProjectAssociation(
  projIdOrKey: String,
  schemeId: String,
  builder: suspend HttpRequestBuilder.() -> Unit = {},
) {
  val response = delete(urlString = """api/2/issuetypescheme/${schemeId}/associations/${projIdOrKey}""") {
    builder()
  }
}
