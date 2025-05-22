package com.example.client

import com.example.DataProvider
import com.example.SynchronizationRunWithConfiguration
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.parameter
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType.Application.Json
import io.ktor.http.contentType
import kotlin.Boolean
import kotlin.String
import kotlin.Unit

/**
 * Starts a new synchronization run using a DataProvider information to obtain the LDIF input, but choose a configuration based on execution group.
 * @param groupName The name of execution group
 * @param start If true the created run will be enqueued to be started
 * @param test If true a dry run without any changes will be performed. This parameter requires the start parameter to be set to true as well
 */
public suspend fun HttpClient.createSynchronizationRunWithExecutionGroupAndUrlInput(
  input: DataProvider,
  groupName: String? = null,
  start: Boolean? = false,
  test: Boolean? = false,
  builder: suspend HttpRequestBuilder.() -> Unit = {},
): SynchronizationRunWithConfiguration? {
  val response = post(urlString = """synchronizationRuns/withExecutionGroupAndUrlInput""") {
    parameter("groupName", groupName)
    parameter("start", start)
    parameter("test", test)
    contentType(Json)
    setBody(body = input)
    builder()
  }
  if (response.status.value == 404) {
    return null
  }
  val output = response.body<SynchronizationRunWithConfiguration>()
  return output
}
