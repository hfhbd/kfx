package com.jira.client

import com.jira.IssueTypeSchemeBean
import com.jira.IssueTypeSchemeCreateUpdateBean
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.put
import io.ktor.client.request.setBody
import io.ktor.http.ContentType.Application.Json
import io.ktor.http.contentType
import kotlin.String
import kotlin.Unit

/**
 * Update specified issue type scheme from JSON representation
 * Updates the specified issue type scheme from a JSON representation
 * @param schemeId The id of the issue type scheme to update.
 */
public suspend fun HttpClient.updateIssueTypeScheme(
  input: IssueTypeSchemeCreateUpdateBean,
  schemeId: String,
  builder: suspend HttpRequestBuilder.() -> Unit = {},
): IssueTypeSchemeBean? {
  val response = put(urlString = """api/2/issuetypescheme/${schemeId}""") {
    contentType(Json)
    setBody(body = input)
    builder()
  }
  if (response.status.value == 404) {
    return null
  }
  val output = response.body<IssueTypeSchemeBean>()
  return output
}
