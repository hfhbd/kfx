package com.example.client

import com.example.Input
import com.example.StatusResponse
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
import kotlin.collections.List

/**
 * Creates a synchronization run.
 * @param start If true the created run will be enqueued to be started
 * @param test If true a dry run without any changes will be performed. This parameter requires the start parameter to be set to true as well
 */
public suspend fun HttpClient.createSynchronizationRun(
  input: Input,
  start: Boolean? = false,
  test: Boolean? = false,
  builder: suspend HttpRequestBuilder.() -> Unit = {},
): List<StatusResponse> {
  val response = post(urlString = """synchronizationRuns""") {
    parameter("start", start)
    parameter("test", test)
    contentType(Json)
    setBody(body = input)
    builder()
  }
  val output = response.body<List<StatusResponse>>()
  return output
}
