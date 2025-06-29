package com.example.client

import com.example.Warning
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.`get`
import io.ktor.client.request.parameter
import io.ktor.http.ContentType.Application.Json
import io.ktor.http.contentType
import kotlin.Int
import kotlin.String
import kotlin.Unit
import kotlin.collections.List

/**
 * Returns the warnings of a synchronization run
 *
 * @param id The ID of the synchronization run
 * @param offset The zero-based index of the first element to retrieve
 * @param limit The number of elements that should be retrieved
 */
public suspend fun HttpClient.getSynchronizationRunWarnings(
  id: String,
  offset: Int? = 0,
  limit: Int? = 100,
  builder: suspend HttpRequestBuilder.() -> Unit = {},
): List<Warning> {
  val response = `get`(urlString = """synchronizationRuns/${id}/warnings""") {
    parameter("offset", offset)
    parameter("limit", limit)
    contentType(Json)
    builder()
  }
  val output = response.body<List<Warning>>()
  return output
}
