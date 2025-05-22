package com.jira.client

import com.jira.IpdMonitoringRestEntity
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.`get`
import kotlin.Unit

/**
 * Get if IPD Monitoring is enabled
 * Checks if IPD Monitoring is enabled
 */
public suspend fun HttpClient.isIpdMonitoringEnabled(builder: suspend HttpRequestBuilder.() -> Unit = {}): IpdMonitoringRestEntity {
  val response = `get`(urlString = """api/2/monitoring/ipd""") {
    builder()
  }
  val output = response.body<IpdMonitoringRestEntity>()
  return output
}
