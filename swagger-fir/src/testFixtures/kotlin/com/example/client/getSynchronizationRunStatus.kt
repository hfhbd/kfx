package com.example.client

import com.example.StatusResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.`get`
import io.ktor.http.ContentType.Application.Json
import io.ktor.http.contentType
import kotlin.String
import kotlin.Unit

/**
 * Returns the status of an existing synchronization run
 *
 * @param id The ID of the synchronization run
 */
public suspend fun HttpClient.getSynchronizationRunStatus(id: String, builder: suspend HttpRequestBuilder.() -> Unit = {}): StatusResponse? {
  val response = `get`(urlString = """synchronizationRuns/${id}/status""") {
    contentType(Json)
    builder()
  }
  if (response.status.value == 404) {
    return null
  }
  val output = response.body<StatusResponse>()
  return output
}
