package com.jira.client

import io.ktor.client.HttpClient
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.delete
import kotlin.Long
import kotlin.Unit

/**
 * Delete project category
 * Delete a project category.
 */
public suspend fun HttpClient.removeProjectCategory(id: Long, builder: suspend HttpRequestBuilder.() -> Unit = {}) {
  val response = delete(urlString = """api/2/projectCategory/${id}""") {
    builder()
  }
}
