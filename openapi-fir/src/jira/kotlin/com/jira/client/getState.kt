package com.jira.client

import com.jira.ClusterState
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.`get`
import kotlin.Unit

/**
 * Get cluster upgrade state
 * Returns the current state of the cluster upgrade.
 */
public suspend fun HttpClient.getState(builder: suspend HttpRequestBuilder.() -> Unit = {}): ClusterState {
  val response = `get`(urlString = """api/2/cluster/zdu/state""") {
    builder()
  }
  val output = response.body<ClusterState>()
  return output
}
