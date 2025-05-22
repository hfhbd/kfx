package com.example.client

import com.example.StatusResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.`get`
import io.ktor.http.ContentType.Application.Json
import io.ktor.http.contentType
import kotlin.Unit
import kotlin.collections.List

/**
 * Returns the status of all existing synchronization runs
 */
public suspend fun HttpClient.getSynchronizationRunsStatusList(builder: suspend HttpRequestBuilder.() -> Unit = {}): List<StatusResponse> {
  val response = `get`(urlString = """synchronizationRuns""") {
    contentType(Json)
    builder()
  }
  val output = response.body<List<StatusResponse>>()
  return output
}
