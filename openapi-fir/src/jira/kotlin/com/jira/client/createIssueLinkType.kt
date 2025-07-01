package com.jira.client

import com.jira.IssueLinkTypeJsonBean
import io.ktor.client.HttpClient
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType.Application.Json
import io.ktor.http.contentType
import kotlin.Unit

/**
 * Create a new issue link type
 * Create a new issue link type.
 */
public suspend fun HttpClient.createIssueLinkType(input: IssueLinkTypeJsonBean, builder: suspend HttpRequestBuilder.() -> Unit = {}) {
  val response = post(urlString = """api/2/issueLinkType""") {
    contentType(Json)
    setBody(body = input)
    builder()
  }
}
