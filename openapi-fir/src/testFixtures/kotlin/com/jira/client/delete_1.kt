package com.jira.client

import com.jira.DeleteAndReplaceVersionBean
import io.ktor.client.HttpClient
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType.Application.Json
import io.ktor.http.contentType
import kotlin.String
import kotlin.Unit

/**
 * Delete version and replace values
 * Delete a project version, removed values will be replaced with ones specified by the parameters.
 */
public suspend fun HttpClient.delete_1(
  input: DeleteAndReplaceVersionBean,
  id: String,
  builder: suspend HttpRequestBuilder.() -> Unit = {},
) {
  val response = post(urlString = """api/2/version/${id}/removeAndSwap""") {
    contentType(Json)
    setBody(body = input)
    builder()
  }
}
