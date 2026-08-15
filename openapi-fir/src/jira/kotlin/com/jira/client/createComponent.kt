package com.jira.client

import com.jira.ComponentBean
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType.Application.Json
import io.ktor.http.HttpStatusCode.Companion.NotFound
import io.ktor.http.contentType
import kotlin.Unit

/**
 * Create component
 * Create a component via POST.
 */
public suspend fun HttpClient.createComponent(input: ComponentBean, builder: suspend HttpRequestBuilder.() -> Unit = {}): ComponentBean? {
  val response = post(urlString = """api/2/component""") {
    contentType(Json)
    setBody(input)
    builder()
  }
  if (response.status == NotFound) {
    return null
  }
  val output = response.body<ComponentBean>()
  return output
}
