package com.jira.client

import com.jira.IpdMonitoringRestEntity
import io.ktor.client.HttpClient
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType.Application.Json
import io.ktor.http.contentType
import kotlin.Unit

/**
 * Update IPD Monitoring status
 * Enables or disables IPD Monitoring
 */
public suspend fun HttpClient.setAppMonitoringEnabled_1(input: IpdMonitoringRestEntity, builder: suspend HttpRequestBuilder.() -> Unit = {}) {
  val response = post(urlString = """api/2/monitoring/ipd""") {
    contentType(Json)
    setBody(body = input)
    builder()
  }
}
