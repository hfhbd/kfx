package com.example.client

import com.example.ProcessorConfiguration
import io.ktor.client.HttpClient
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.put
import io.ktor.client.request.setBody
import io.ktor.http.ContentType.Application.Json
import io.ktor.http.contentType
import kotlin.Unit

/**
 * Inserts a new processor configuration or updates an existing one
 */
public suspend fun HttpClient.upsertProcessorConfiguration(input: ProcessorConfiguration, builder: suspend HttpRequestBuilder.() -> Unit = {}) {
  val response = put(urlString = """configurations""") {
    contentType(Json)
    setBody(body = input)
    builder()
  }
}
