package com.jira.client

import io.ktor.client.HttpClient
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.delete
import kotlin.Long
import kotlin.Unit

/**
 * Delete a sprint
 * Deletes a sprint. Once a sprint is deleted, all issues in the sprint will be moved to the backlog. To delete a synced sprint, you must unsync it first.
 */
public suspend fun HttpClient.deleteSprint(sprintId: Long, builder: suspend HttpRequestBuilder.() -> Unit = {}) {
  val response = delete(urlString = """agile/1.0/sprint/${sprintId}""") {
    builder()
  }
}
