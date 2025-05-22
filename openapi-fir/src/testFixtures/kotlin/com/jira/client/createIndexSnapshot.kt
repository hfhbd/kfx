package com.jira.client

import com.jira.IndexSnapshotPromiseBean
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.post
import kotlin.Unit

/**
 * Create index snapshot if not in progress
 * Starts taking an index snapshot if no other snapshot creation process is in progress
 */
public suspend fun HttpClient.createIndexSnapshot(builder: suspend HttpRequestBuilder.() -> Unit = {}): IndexSnapshotPromiseBean {
  val response = post(urlString = """api/2/index-snapshot""") {
    builder()
  }
  val output = response.body<IndexSnapshotPromiseBean>()
  return output
}
