package com.jira.client

import io.ktor.client.HttpClient
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.delete
import io.ktor.client.request.setBody
import io.ktor.http.ContentType.Application.Json
import io.ktor.http.contentType
import kotlin.String
import kotlin.Unit

/**
 * Invalidate the current WebSudo session
 * This method invalidates the any current WebSudo session.
 */
public suspend fun HttpClient.release(input: String, builder: suspend HttpRequestBuilder.() -> Unit = {}) {
  val response = delete(urlString = """auth/1/websudo""") {
    contentType(Json)
    setBody(body = input)
    builder()
  }
}
