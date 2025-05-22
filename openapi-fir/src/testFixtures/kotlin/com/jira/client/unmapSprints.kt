package com.jira.client

import com.jira.UnmapSprintsBean
import io.ktor.client.HttpClient
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.put
import io.ktor.client.request.setBody
import io.ktor.http.ContentType.Application.Json
import io.ktor.http.contentType
import kotlin.Unit

/**
 * Unmap sprints from being synced
 * Sets the Synced flag to false for all sprints in the provided list.
 */
public suspend fun HttpClient.unmapSprints(input: UnmapSprintsBean, builder: suspend HttpRequestBuilder.() -> Unit = {}) {
  val response = put(urlString = """agile/1.0/sprint/unmap""") {
    contentType(Json)
    setBody(body = input)
    builder()
  }
}
