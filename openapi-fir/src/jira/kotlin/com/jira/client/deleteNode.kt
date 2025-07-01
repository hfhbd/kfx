package com.jira.client

import io.ktor.client.HttpClient
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.delete
import kotlin.String
import kotlin.Unit

/**
 * Delete a cluster node
 * Delete the node from the cluster if state of node is OFFLINE.
 *
 * @param nodeId ID of the node to delete
 */
public suspend fun HttpClient.deleteNode(nodeId: String, builder: suspend HttpRequestBuilder.() -> Unit = {}) {
  val response = delete(urlString = """api/2/cluster/node/${nodeId}""") {
    builder()
  }
}
