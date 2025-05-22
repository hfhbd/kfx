package com.jira.client

import com.jira.MoveFieldBean
import io.ktor.client.HttpClient
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType.Application.Json
import io.ktor.http.contentType
import kotlin.Long
import kotlin.String
import kotlin.Unit

/**
 * Move field on a tab
 * Moves field on the given tab.
 */
public suspend fun HttpClient.moveField(
  input: MoveFieldBean,
  tabId: Long,
  screenId: Long,
  id: String,
  builder: suspend HttpRequestBuilder.() -> Unit = {},
) {
  val response = post(urlString = """api/2/screens/${screenId}/tabs/${tabId}/fields/${id}/move""") {
    contentType(Json)
    setBody(body = input)
    builder()
  }
}
