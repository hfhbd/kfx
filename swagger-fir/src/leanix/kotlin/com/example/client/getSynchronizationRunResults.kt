package com.example.client

import com.example.LeanIxDataInterchangeFormat
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.`get`
import io.ktor.http.HttpStatusCode.Companion.NotFound
import kotlin.String
import kotlin.Unit

/**
 * Returns the results of a finished synchronization run
 *
 * @param id The ID of the synchronization run
 */
public suspend fun HttpClient.getSynchronizationRunResults(id: String, builder: suspend HttpRequestBuilder.() -> Unit = {}): LeanIxDataInterchangeFormat? {
  val response = `get`(urlString = """synchronizationRuns/${id}/results""") {
    builder()
  }
  when {
    response.status == NotFound -> {
      return null
    }
  }
  val output = response.body<LeanIxDataInterchangeFormat>()
  return output
}
