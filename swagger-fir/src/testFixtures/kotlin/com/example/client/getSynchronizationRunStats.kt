package com.example.client

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.`get`
import io.ktor.http.ContentType.Application.Json
import io.ktor.http.contentType
import kotlin.String
import kotlin.Unit

/**
 * Returns detailed statistics about the execution of a synchronization run
 *
 * @param id The ID of the synchronization run
 */
public suspend fun HttpClient.getSynchronizationRunStats(id: String, builder: suspend HttpRequestBuilder.() -> Unit = {}) {
  val response = `get`(urlString = """synchronizationRuns/${id}/stats""") {
    contentType(Json)
    builder()
  }
  val output = response.body<Unit>()
  return output
}
