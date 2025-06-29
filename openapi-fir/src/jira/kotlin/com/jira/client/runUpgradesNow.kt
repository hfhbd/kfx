package com.jira.client

import io.ktor.client.HttpClient
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.post
import kotlin.Unit

/**
 * Run pending upgrade tasks
 * Runs any pending delayed upgrade tasks. Need Admin permissions to do this.
 */
public suspend fun HttpClient.runUpgradesNow(builder: suspend HttpRequestBuilder.() -> Unit = {}) {
  val response = post(urlString = """api/2/upgrade""") {
    builder()
  }
}
