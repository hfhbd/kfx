package com.example.client

import com.example.SyncRunInboundProgressReport
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.`get`
import io.ktor.http.ContentType.Application.Json
import io.ktor.http.contentType
import kotlin.String
import kotlin.Unit

/**
 * Shows the progress of a synchronization run, it gives updated counters of the run level that is in execution.
 *
 * @param id The ID of the synchronization run
 */
public suspend fun HttpClient.getSynchronizationRunProgress(id: String, builder: suspend HttpRequestBuilder.() -> Unit = {}): SyncRunInboundProgressReport? {
  val response = `get`(urlString = """synchronizationRuns/${id}/progress""") {
    contentType(Json)
    builder()
  }
  if (response.status.value == 404) {
    return null
  }
  val output = response.body<SyncRunInboundProgressReport>()
  return output
}
