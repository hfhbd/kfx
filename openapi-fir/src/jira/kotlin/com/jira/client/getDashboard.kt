package com.jira.client

import com.jira.DashboardBean
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.`get`
import kotlin.String
import kotlin.Unit

/**
 * Get a single dashboard by ID
 * Returns a single dashboard.
 *
 * @param id The dashboard id.
 */
public suspend fun HttpClient.getDashboard(id: String, builder: suspend HttpRequestBuilder.() -> Unit = {}): DashboardBean? {
  val response = `get`(urlString = """api/2/dashboard/${id}""") {
    builder()
  }
  if (response.status.value == 404) {
    return null
  }
  val output = response.body<DashboardBean>()
  return output
}
