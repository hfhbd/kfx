package com.example.client

import com.example.InputWithProcessorConfig
import com.example.SynchronizationRun
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
 * Starts a new synchronization run using the processor configuration and input object provided in the request.
 * >__Please do not use this endpoint for production use cases. It was built for testing configurations only.__
 * @param start If true the created run will be enqueued to be started
 * @param test If true a dry run without any changes will be performed. This parameter requires the start parameter to be set to true as well
 */
public suspend fun HttpClient.createSynchronizationRunWithConfig(
  input: InputWithProcessorConfig,
  start: Boolean? = false,
  test: Boolean? = false,
  builder: suspend HttpRequestBuilder.() -> Unit = {},
): SynchronizationRun {
  val response = post(urlString = """synchronizationRuns/withConfig""") {
    parameter("start", start)
    parameter("test", test)
    contentType(Json)
    setBody(body = input)
    builder()
  }
  val output = response.body<SynchronizationRun>()
  return output
}
