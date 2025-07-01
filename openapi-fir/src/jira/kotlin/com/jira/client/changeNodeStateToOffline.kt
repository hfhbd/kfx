package com.jira.client

import io.ktor.client.HttpClient
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.put
import kotlin.String
import kotlin.Unit

/**
 * Update node state to offline
 * Change the node's state to offline if the node is reporting as active, but is not alive.
 *
 * @param nodeId ID of the node to change state
 */
public suspend fun HttpClient.changeNodeStateToOffline(nodeId: String, builder: suspend HttpRequestBuilder.() -> Unit = {}) {
  val response = put(urlString = """api/2/cluster/node/${nodeId}/offline""") {
    builder()
  }
}
