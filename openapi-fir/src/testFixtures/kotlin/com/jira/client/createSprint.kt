package com.jira.client

import com.jira.SprintBean
import com.jira.SprintCreateBean
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType.Application.Json
import io.ktor.http.contentType
import kotlin.Unit

/**
 * Create a future sprint
 * Creates a future sprint. Sprint name and origin board id are required. Start and end date are optional. Notes: The sprint name is trimmed. Only Jira administrators can create synced sprints.
 */
public suspend fun HttpClient.createSprint(input: SprintCreateBean, builder: suspend HttpRequestBuilder.() -> Unit = {}): SprintBean? {
  val response = post(urlString = """agile/1.0/sprint""") {
    contentType(Json)
    setBody(body = input)
    builder()
  }
  if (response.status.value == 404) {
    return null
  }
  val output = response.body<SprintBean>()
  return output
}
