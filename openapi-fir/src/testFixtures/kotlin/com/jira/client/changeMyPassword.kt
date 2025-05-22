package com.jira.client

import com.jira.PasswordBean
import io.ktor.client.HttpClient
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.put
import io.ktor.client.request.setBody
import io.ktor.http.ContentType.Application.Json
import io.ktor.http.contentType
import kotlin.Unit

/**
 * Update caller password
 * Modify caller password.
 */
public suspend fun HttpClient.changeMyPassword(input: PasswordBean, builder: suspend HttpRequestBuilder.() -> Unit = {}) {
  val response = put(urlString = """api/2/myself/password""") {
    contentType(Json)
    setBody(body = input)
    builder()
  }
}
