package com.jira.client

import com.jira.IndexSnapshotStatusBean
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.`get`
import kotlin.Unit

/**
 * Get index snapshot creation status
 * Checks if index snapshot creation is currently running
 */
public suspend fun HttpClient.isIndexSnapshotRunning(builder: suspend HttpRequestBuilder.() -> Unit = {}): IndexSnapshotStatusBean {
  val response = `get`(urlString = """api/2/index-snapshot/isRunning""") {
    builder()
  }
  val output = response.body<IndexSnapshotStatusBean>()
  return output
}
