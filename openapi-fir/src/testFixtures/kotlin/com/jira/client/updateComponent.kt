package com.jira.client

import com.jira.ComponentBean
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.put
import io.ktor.client.request.setBody
import io.ktor.http.ContentType.Application.Json
import io.ktor.http.contentType
import kotlin.String
import kotlin.Unit

/**
 * Update a component
 * Modify a component via PUT. Any fields present in the PUT will override existing values. As a convenience, if a field is not present, it is silently ignored.
 * @param id The component to delete.
 */
public suspend fun HttpClient.updateComponent(
  input: ComponentBean,
  id: String,
  builder: suspend HttpRequestBuilder.() -> Unit = {},
): ComponentBean? {
  val response = put(urlString = """api/2/component/${id}""") {
    contentType(Json)
    setBody(body = input)
    builder()
  }
  if (response.status.value == 404) {
    return null
  }
  val output = response.body<ComponentBean>()
  return output
}
