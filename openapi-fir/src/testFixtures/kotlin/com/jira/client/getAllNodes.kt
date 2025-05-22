package com.jira.client

import com.jira.NodeBean
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.`get`
import kotlin.Unit

/**
 * Get all cluster nodes
 * Returns all nodes in cluster.
 */
public suspend fun HttpClient.getAllNodes(builder: suspend HttpRequestBuilder.() -> Unit = {}): NodeBean {
  val response = `get`(urlString = """api/2/cluster/nodes""") {
    builder()
  }
  val output = response.body<NodeBean>()
  return output
}
