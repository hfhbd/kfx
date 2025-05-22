package com.jira.client

import io.ktor.client.HttpClient
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.`get`
import io.ktor.client.request.parameter
import kotlin.Long
import kotlin.Unit

/**
 * Get user anonymization progress
 * Returns information about a user anonymization operation progress.
 */
public suspend fun HttpClient.getProgress_1(taskId: Long? = null, builder: suspend HttpRequestBuilder.() -> Unit = {}) {
  val response = `get`(urlString = """api/2/user/anonymization/progress""") {
    parameter("taskId", taskId)
    builder()
  }
}
