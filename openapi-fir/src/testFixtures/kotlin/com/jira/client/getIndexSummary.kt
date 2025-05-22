package com.jira.client

import com.jira.IndexSummaryBean
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.`get`
import kotlin.Unit

/**
 * Get index condition summary
 * Returns a summary of the index condition of the current node.
 * The returned data consists of:
 * - `nodeId` - Node identifier.
 * - `reportTime` - Time of this report creation.
 * - `issueIndex` - Summary of the issue index status.
 * - `replicationQueues` - Map of index replication queues, where keys represent nodes from which replication operations came from.
 *
 * `issueIndex` can contain:
 *     - `indexReadable` - If `false` the endpoint failed to read data from the issue index (check Jira logs for detailed stack trace), otherwise `true`.
 *     - `countInDatabase` - Count of issues found in the database.
 *     - `countInIndex` - Count of issues found while querying the index.
 *     - `lastUpdatedInDatabase` - Time of the last update of the issue found in the database.
 *     - `lastUpdatedInIndex` - Time of the last update of the issue found while querying the index.
 * `replicationQueues`'s map values can contain:
 *     - `lastConsumedOperation` - Last executed index replication operation by the current node from the sending node's queue.
 *     - `lastConsumedOperation.id` - Identifier of the operation.
 *     - `lastConsumedOperation.replicationTime` - Time when the operation was sent to other nodes.
 *     - `lastOperationInQueue` - Last index replication operation in the sending node's queue.
 *     - `lastOperationInQueue.id` - Identifier of the operation.
 *     - `lastOperationInQueue.replicationTime` - Time when the operation was sent to other nodes.
 *     - `queueSize` - Number of operations in the queue from the sending node to the current node.
 */
public suspend fun HttpClient.getIndexSummary(builder: suspend HttpRequestBuilder.() -> Unit = {}): IndexSummaryBean {
  val response = `get`(urlString = """api/2/index/summary""") {
    builder()
  }
  val output = response.body<IndexSummaryBean>()
  return output
}
