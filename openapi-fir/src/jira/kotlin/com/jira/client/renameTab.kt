package com.jira.client

import com.jira.ScreenableTabBean
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
 * Rename a tab on a screen
 * Renames tab on given screen.
 */
public suspend fun HttpClient.renameTab(
  input: ScreenableTabBean,
  tabId: Long,
  screenId: Long,
  builder: suspend HttpRequestBuilder.() -> Unit = {},
): ScreenableTabBean {
  val response = put(urlString = """api/2/screens/${screenId}/tabs/${tabId}""") {
    contentType(Json)
    setBody(body = input)
    builder()
  }
  val output = response.body<ScreenableTabBean>()
  return output
}
