package com.jira.client

import com.jira.VersionBean
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType.Application.Json
import io.ktor.http.contentType
import kotlin.Unit

/**
 * Create new version
 * Creates a version.
 */
public suspend fun HttpClient.createVersion(input: VersionBean, builder: suspend HttpRequestBuilder.() -> Unit = {}): VersionBean {
  val response = post(urlString = """api/2/version""") {
    contentType(Json)
    setBody(body = input)
    builder()
  }
  val output = response.body<VersionBean>()
  return output
}
