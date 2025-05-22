package com.jira.client

import com.jira.AddFieldBean
import com.jira.ScreenableFieldBean
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
 * Add field to a tab
 * Adds field to the given tab.
 */
public suspend fun HttpClient.addField(
  input: AddFieldBean,
  tabId: Long,
  screenId: Long,
  builder: suspend HttpRequestBuilder.() -> Unit = {},
): ScreenableFieldBean {
  val response = post(urlString = """api/2/screens/${screenId}/tabs/${tabId}/fields""") {
    contentType(Json)
    setBody(body = input)
    builder()
  }
  val output = response.body<ScreenableFieldBean>()
  return output
}
