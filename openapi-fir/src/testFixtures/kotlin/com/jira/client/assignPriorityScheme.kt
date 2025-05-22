package com.jira.client

import com.jira.IdBean
import com.jira.PrioritySchemeBean
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
 * Assign project with priority scheme
 * Assigns project with priority scheme. Priority scheme assign with migration is possible from the UI. Operation will fail if migration is needed as a result of operation eg. there are issues with priorities invalid in the destination scheme. All project keys associated with the priority scheme will only be returned if additional query parameter is provided expand=projectKeys.
 */
public suspend fun HttpClient.assignPriorityScheme(
  input: IdBean,
  projectKeyOrId: String,
  builder: suspend HttpRequestBuilder.() -> Unit = {},
): PrioritySchemeBean? {
  val response = put(urlString = """api/2/project/${projectKeyOrId}/priorityscheme""") {
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
