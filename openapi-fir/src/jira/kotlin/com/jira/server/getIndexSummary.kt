package com.jira.server

import com.jira.IndexSummaryBean
import io.ktor.http.ContentType.Application.Json
import io.ktor.http.HttpStatusCode.Companion.OK
import io.ktor.server.application.ApplicationCall
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.`get`
import io.ktor.server.routing.accept
import io.ktor.server.routing.route

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
public fun Route.getIndexSummary(action: suspend ApplicationCall.() -> IndexSummaryBean) {
  route(path = """/api/2/index/summary""") {
    accept(Json) {
      `get` {
        val response = call.action()
        call.response.status(OK)
        call.respond(response)
      }
    }
  }
}
