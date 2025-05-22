package com.jira.client

import io.ktor.client.HttpClient
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.post
import kotlin.Unit

/**
 * Cancel cluster upgrade
 * Cancels the ongoing cluster upgrade.
 */
public suspend fun HttpClient.cancelUpgrade(builder: suspend HttpRequestBuilder.() -> Unit = {}) {
  val response = post(urlString = """api/2/cluster/zdu/cancel""") {
    builder()
  }
}
