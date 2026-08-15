package com.example.client

import com.example.UrlContainer
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.`get`
import io.ktor.http.HttpStatusCode.Companion.NotFound
import kotlin.String
import kotlin.Unit

/**
 * Returns the url to the results of a finished synchronization run
 *
 * @param id The ID of the synchronization run
 */
public suspend fun HttpClient.getSynchronizationRunResultsUrl(id: String, builder: suspend HttpRequestBuilder.() -> Unit = {}): UrlContainer? {
  val response = `get`(urlString = """synchronizationRuns/${id}/resultsUrl""") {
    builder()
  }
  if (response.status == NotFound) {
    return null
  }
  val output = response.body<UrlContainer>()
  return output
}
