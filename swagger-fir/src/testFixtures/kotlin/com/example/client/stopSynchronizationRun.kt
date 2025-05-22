package com.example.client

import com.example.ErrorResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.post
import io.ktor.http.ContentType.Application.Json
import io.ktor.http.contentType
import io.ktor.http.isSuccess
import kotlin.String
import kotlin.Throws
import kotlin.Unit

/**
 * Stops a running synchronization run
 *
 * @param id The ID of the synchronization run
 */
@Throws(ErrorResponse::class)
public suspend fun HttpClient.stopSynchronizationRun(id: String, builder: suspend HttpRequestBuilder.() -> Unit = {}) {
  val response = post(urlString = """synchronizationRuns/${id}/stop""") {
    contentType(Json)
    builder()
  }
  if (response.status.isSuccess()) {
  } else {
    val output = response.body<ErrorResponse>()
    throw output
  }
}
