package com.jira.client

import com.jira.AssociateProjectsBean
import io.ktor.client.HttpClient
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.put
import io.ktor.client.request.setBody
import io.ktor.http.ContentType.Application.Json
import io.ktor.http.contentType
import kotlin.String
import kotlin.Unit

/**
 * Set project associations for scheme
 * Associates the given projects with the specified issue type scheme
 * @param schemeId The id of the issue type scheme whose project associations we're replacing.
 */
public suspend fun HttpClient.setProjectAssociationsForScheme(
  input: AssociateProjectsBean,
  schemeId: String,
  builder: suspend HttpRequestBuilder.() -> Unit = {},
) {
  val response = put(urlString = """api/2/issuetypescheme/${schemeId}/associations""") {
    contentType(Json)
    setBody(body = input)
    builder()
  }
}
