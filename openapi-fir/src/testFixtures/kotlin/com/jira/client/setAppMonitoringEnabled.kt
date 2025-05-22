package com.jira.client

import com.jira.AppMonitoringRestEntity
import io.ktor.client.HttpClient
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType.Application.Json
import io.ktor.http.contentType
import kotlin.Unit

/**
 * Update App Monitoring status
 * Enables or disables App Monitoring
 */
public suspend fun HttpClient.setAppMonitoringEnabled(input: AppMonitoringRestEntity, builder: suspend HttpRequestBuilder.() -> Unit = {}) {
  val response = post(urlString = """api/2/monitoring/app""") {
    contentType(Json)
    setBody(body = input)
    builder()
  }
}
