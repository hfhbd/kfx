package com.jira.client

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.`get`
import kotlin.Boolean
import kotlin.String
import kotlin.Unit

/**
 * Check if a subtask can be moved
 * Checks if a subtask can be moved
 *
 * @param issueIdOrKey The parent issue's key or id
 */
public suspend fun HttpClient.canMoveSubTask(issueIdOrKey: String, builder: suspend HttpRequestBuilder.() -> Unit = {}): Boolean {
  val response = `get`(urlString = """api/2/issue/${issueIdOrKey}/subtask/move""") {
    builder()
  }
  val output = response.body<Boolean>()
  return output
}
