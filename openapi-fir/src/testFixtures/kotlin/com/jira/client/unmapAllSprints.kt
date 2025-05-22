package com.jira.client

import io.ktor.client.HttpClient
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.put
import kotlin.Unit

/**
 * Unmap all sprints from being synced
 * Sets the Synced flag to false for all sprints on this Jira instance. This operation is intended for cleanup only. It is highly destructive and not reversible. Use with caution.
 */
public suspend fun HttpClient.unmapAllSprints(builder: suspend HttpRequestBuilder.() -> Unit = {}) {
  val response = put(urlString = """agile/1.0/sprint/unmap-all""") {
    builder()
  }
}
