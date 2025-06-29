package com.jira.client

import io.ktor.client.HttpClient
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.delete
import kotlin.String
import kotlin.Unit

/**
 * Remove all project associations for specified scheme
 * Removes all project associations for the specified issue type scheme
 *
 * @param schemeId The id of the issue type scheme whose project associations we're removing
 */
public suspend fun HttpClient.removeAllProjectAssociations(schemeId: String, builder: suspend HttpRequestBuilder.() -> Unit = {}) {
  val response = delete(urlString = """api/2/issuetypescheme/${schemeId}/associations""") {
    builder()
  }
}
