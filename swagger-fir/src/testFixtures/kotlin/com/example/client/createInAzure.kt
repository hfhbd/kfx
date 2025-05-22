package com.example.client

import com.example.StorageManagerResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.`get`
import io.ktor.http.ContentType.Application.Json
import io.ktor.http.contentType
import kotlin.Unit

/**
 * Provides storage resources that can be used for synchronisation runs. It creates a blob file in Azure Storage.
 */
public suspend fun HttpClient.createInAzure(builder: suspend HttpRequestBuilder.() -> Unit = {}): StorageManagerResponse {
  val response = `get`(urlString = """storages/azure""") {
    contentType(Json)
    builder()
  }
  val output = response.body<StorageManagerResponse>()
  return output
}
