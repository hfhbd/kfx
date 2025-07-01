package com.jira.client

import io.ktor.client.HttpClient
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.post
import kotlin.Unit

/**
 * Approve cluster upgrade
 * Approves the cluster upgrade.
 */
public suspend fun HttpClient.approveUpgrade(builder: suspend HttpRequestBuilder.() -> Unit = {}) {
  val response = post(urlString = """api/2/cluster/zdu/approve""") {
    builder()
  }
}
