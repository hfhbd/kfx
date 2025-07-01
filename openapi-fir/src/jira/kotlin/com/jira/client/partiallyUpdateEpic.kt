package com.jira.client

import com.jira.EpicBean
import com.jira.EpicUpdateBean
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType.Application.Json
import io.ktor.http.contentType
import kotlin.String
import kotlin.Unit

/**
 * Update an epic's details
 * Performs a partial update of the epic. A partial update means that fields not present in the request JSON will not be updated. Valid values for color are color_1 to color_9.
 */
public suspend fun HttpClient.partiallyUpdateEpic(
  input: EpicUpdateBean,
  epicIdOrKey: String,
  builder: suspend HttpRequestBuilder.() -> Unit = {},
): EpicBean? {
  val response = post(urlString = """agile/1.0/epic/${epicIdOrKey}""") {
    contentType(Json)
    setBody(body = input)
    builder()
  }
  if (response.status.value == 404) {
    return null
  }
  val output = response.body<EpicBean>()
  return output
}
