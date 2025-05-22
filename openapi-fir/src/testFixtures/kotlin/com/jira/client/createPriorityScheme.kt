package com.jira.client

import com.jira.PrioritySchemeBean
import com.jira.PrioritySchemeUpdateBean
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType.Application.Json
import io.ktor.http.contentType
import kotlin.Unit

/**
 * Create new priority scheme
 * Creates new priority scheme.
 */
public suspend fun HttpClient.createPriorityScheme(input: PrioritySchemeUpdateBean, builder: suspend HttpRequestBuilder.() -> Unit = {}): PrioritySchemeBean {
  val response = post(urlString = """api/2/priorityschemes""") {
    contentType(Json)
    setBody(body = input)
    builder()
  }
  val output = response.body<PrioritySchemeBean>()
  return output
}
