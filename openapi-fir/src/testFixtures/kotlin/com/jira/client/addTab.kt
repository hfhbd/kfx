package com.jira.client

import com.jira.ScreenableTabBean
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType.Application.Json
import io.ktor.http.contentType
import kotlin.Long
import kotlin.Unit

/**
 * Create tab for a screen
 * Creates tab for given screen.
 */
public suspend fun HttpClient.addTab(
  input: ScreenableTabBean,
  screenId: Long,
  builder: suspend HttpRequestBuilder.() -> Unit = {},
): ScreenableTabBean {
  val response = post(urlString = """api/2/screens/${screenId}/tabs""") {
    contentType(Json)
    setBody(body = input)
    builder()
  }
  val output = response.body<ScreenableTabBean>()
  return output
}
