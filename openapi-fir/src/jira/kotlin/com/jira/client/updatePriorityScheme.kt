package com.jira.client

import com.jira.PrioritySchemeBean
import com.jira.PrioritySchemeUpdateBean
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.put
import io.ktor.client.request.setBody
import io.ktor.http.ContentType.Application.Json
import io.ktor.http.contentType
import kotlin.Long
import kotlin.Unit

/**
 * Update a priority scheme
 * Updates a priority scheme. Update will be rejected if issue migration would be needed as a result of scheme update. Priority scheme update with migration is possible from the UI.
 */
public suspend fun HttpClient.updatePriorityScheme(
  input: PrioritySchemeUpdateBean,
  schemeId: Long,
  builder: suspend HttpRequestBuilder.() -> Unit = {},
): PrioritySchemeBean? {
  val response = put(urlString = """api/2/priorityschemes/${schemeId}""") {
    contentType(Json)
    setBody(body = input)
    builder()
  }
  if (response.status.value == 404) {
    return null
  }
  val output = response.body<PrioritySchemeBean>()
  return output
}
