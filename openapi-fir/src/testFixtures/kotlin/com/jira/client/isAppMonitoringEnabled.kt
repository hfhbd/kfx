package com.jira.client

import com.jira.AppMonitoringRestEntity
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.`get`
import kotlin.Unit

/**
 * Get App Monitoring status
 * Checks if App Monitoring is enabled
 */
public suspend fun HttpClient.isAppMonitoringEnabled(builder: suspend HttpRequestBuilder.() -> Unit = {}): AppMonitoringRestEntity {
  val response = `get`(urlString = """api/2/monitoring/app""") {
    builder()
  }
  val output = response.body<AppMonitoringRestEntity>()
  return output
}
