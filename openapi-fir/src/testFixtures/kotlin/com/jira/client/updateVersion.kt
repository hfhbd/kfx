package com.jira.client

import com.jira.VersionBean
import io.ktor.client.HttpClient
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.put
import io.ktor.client.request.setBody
import io.ktor.http.ContentType.Application.Json
import io.ktor.http.contentType
import kotlin.String
import kotlin.Unit

/**
 * Update version details
 * Updates a version.
 */
public suspend fun HttpClient.updateVersion(
  input: VersionBean,
  id: String,
  builder: suspend HttpRequestBuilder.() -> Unit = {},
) {
  val response = put(urlString = """api/2/version/${id}""") {
    contentType(Json)
    setBody(body = input)
    builder()
  }
}
