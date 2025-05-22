package com.jira.client

import com.jira.AssociateProjectsBean
import io.ktor.client.HttpClient
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType.Application.Json
import io.ktor.http.contentType
import kotlin.String
import kotlin.Unit

/**
 * Add project associations to scheme
 * Adds additional projects to those already associated with the specified issue type scheme
 * @param schemeId The id of the issue type scheme whose project associations we're adding to.
 */
public suspend fun HttpClient.addProjectAssociationsToScheme(
  input: AssociateProjectsBean,
  schemeId: String,
  builder: suspend HttpRequestBuilder.() -> Unit = {},
) {
  val response = post(urlString = """api/2/issuetypescheme/${schemeId}/associations""") {
    contentType(Json)
    setBody(body = input)
    builder()
  }
}
