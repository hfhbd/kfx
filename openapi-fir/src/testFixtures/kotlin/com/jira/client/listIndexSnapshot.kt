package com.jira.client

import com.jira.IndexSnapshotBean
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.`get`
import kotlin.Unit

/**
 * Get list of available index snapshots
 * Lists available index snapshots absolute paths with timestamps
 */
public suspend fun HttpClient.listIndexSnapshot(builder: suspend HttpRequestBuilder.() -> Unit = {}): IndexSnapshotBean {
  val response = `get`(urlString = """api/2/index-snapshot""") {
    builder()
  }
  val output = response.body<IndexSnapshotBean>()
  return output
}
