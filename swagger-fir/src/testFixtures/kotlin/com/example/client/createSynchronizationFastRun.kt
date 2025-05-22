package com.example.client

import com.example.FastRunResponse
import com.example.Input
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.parameter
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType.Application.Json
import io.ktor.http.contentType
import kotlin.Boolean
import kotlin.Unit

/**
 * Creates a fast synchronization run.
 * @param test If true a dry run without any changes will be performed
 */
public suspend fun HttpClient.createSynchronizationFastRun(
  input: Input,
  test: Boolean? = false,
  builder: suspend HttpRequestBuilder.() -> Unit = {},
): FastRunResponse {
  val response = post(urlString = """fastSynchronizationRuns""") {
    parameter("test", test)
    contentType(Json)
    setBody(body = input)
    builder()
  }
  val output = response.body<FastRunResponse>()
  return output
}
