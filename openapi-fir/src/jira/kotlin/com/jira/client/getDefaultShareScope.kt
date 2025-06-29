package com.jira.client

import com.jira.DefaultShareScopeBean
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.`get`
import kotlin.Unit

/**
 * Get default share scope
 * Returns the default share scope of the logged-in user
 */
public suspend fun HttpClient.getDefaultShareScope(builder: suspend HttpRequestBuilder.() -> Unit = {}): DefaultShareScopeBean {
  val response = `get`(urlString = """api/2/filter/defaultShareScope""") {
    builder()
  }
  val output = response.body<DefaultShareScopeBean>()
  return output
}
