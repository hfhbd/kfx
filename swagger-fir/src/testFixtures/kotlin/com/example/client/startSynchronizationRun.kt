package com.example.client

import com.example.ErrorResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.parameter
import io.ktor.client.request.post
import io.ktor.http.ContentType.Application.Json
import io.ktor.http.contentType
import io.ktor.http.isSuccess
import kotlin.Boolean
import kotlin.String
import kotlin.Throws
import kotlin.Unit

/**
 * Starts an existing but not yet started synchronization run
 *
 * @param id The ID of the synchronization run
 * @param test If true a dry run without any changes will be performed
 */
@Throws(ErrorResponse::class)
public suspend fun HttpClient.startSynchronizationRun(
  id: String,
  test: Boolean? = false,
  builder: suspend HttpRequestBuilder.() -> Unit = {},
) {
  val response = post(urlString = """synchronizationRuns/${id}/start""") {
    parameter("test", test)
    contentType(Json)
    builder()
  }
  if (response.status.isSuccess()) {
  } else {
    val output = response.body<ErrorResponse>()
    throw output
  }
}
