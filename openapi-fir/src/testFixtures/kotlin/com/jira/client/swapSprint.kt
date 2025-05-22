package com.jira.client

import com.jira.SprintSwapBean
import io.ktor.client.HttpClient
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType.Application.Json
import io.ktor.http.contentType
import kotlin.Long
import kotlin.Unit

/**
 * Swap the position of two sprints
 * Swap the position of the sprint with the second sprint.
 */
public suspend fun HttpClient.swapSprint(
  input: SprintSwapBean,
  sprintId: Long,
  builder: suspend HttpRequestBuilder.() -> Unit = {},
) {
  val response = post(urlString = """agile/1.0/sprint/${sprintId}/swap""") {
    contentType(Json)
    setBody(body = input)
    builder()
  }
}
