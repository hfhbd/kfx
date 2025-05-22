package com.jira.client

import com.jira.UpgradeResultBean
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.`get`
import kotlin.Unit

/**
 * Get result of the last upgrade task
 * Returns the result of the last upgrade task.
 */
public suspend fun HttpClient.getUpgradeResult(builder: suspend HttpRequestBuilder.() -> Unit = {}): UpgradeResultBean? {
  val response = `get`(urlString = """api/2/upgrade""") {
    builder()
  }
  if (response.status.value == 404) {
    return null
  }
  val output = response.body<UpgradeResultBean>()
  return output
}
