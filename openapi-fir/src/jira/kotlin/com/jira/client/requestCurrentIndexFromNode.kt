package com.jira.client

import io.ktor.client.HttpClient
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.put
import kotlin.String
import kotlin.Unit

/**
 * Request node index snapshot
 * Request current index from node (the request is processed asynchronously)
 *
 * @param nodeId ID of the node to request index from
 */
public suspend fun HttpClient.requestCurrentIndexFromNode(nodeId: String, builder: suspend HttpRequestBuilder.() -> Unit = {}) {
  val response = put(urlString = """api/2/cluster/index-snapshot/${nodeId}""") {
    builder()
  }
}
