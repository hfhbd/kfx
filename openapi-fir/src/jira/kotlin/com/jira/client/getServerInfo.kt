package com.jira.client

import com.jira.ServerInfoBean
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.`get`
import kotlin.Unit

/**
 * Get general information about the current Jira server
 * Returns general information about the current Jira server.
 */
public suspend fun HttpClient.getServerInfo(builder: suspend HttpRequestBuilder.() -> Unit = {}): ServerInfoBean {
  val response = `get`(urlString = """api/2/serverInfo""") {
    builder()
  }
  val output = response.body<ServerInfoBean>()
  return output
}
