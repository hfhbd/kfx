package com.jira.client

import com.jira.DefaultShareScopeBean
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.put
import io.ktor.client.request.setBody
import io.ktor.http.ContentType.Application.Json
import io.ktor.http.contentType
import kotlin.Unit

/**
 * Set default share scope
 * Sets the default share scope of the logged-in user. Available values are: AUTHENTICATED (for sharing with all logged-in users) and PRIVATE (for no shares).
 */
public suspend fun HttpClient.setDefaultShareScope(input: DefaultShareScopeBean, builder: suspend HttpRequestBuilder.() -> Unit = {}): DefaultShareScopeBean {
  val response = put(urlString = """api/2/filter/defaultShareScope""") {
    contentType(Json)
    setBody(body = input)
    builder()
  }
  val output = response.body<DefaultShareScopeBean>()
  return output
}
