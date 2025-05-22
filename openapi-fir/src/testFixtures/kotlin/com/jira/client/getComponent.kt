package com.jira.client

import com.jira.ComponentBean
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.`get`
import kotlin.String
import kotlin.Unit

/**
 * Get project component
 * Returns a project component.
 *
 * @param id a String containing the component key
 */
public suspend fun HttpClient.getComponent(id: String, builder: suspend HttpRequestBuilder.() -> Unit = {}): ComponentBean? {
  val response = `get`(urlString = """api/2/component/${id}""") {
    builder()
  }
  if (response.status.value == 404) {
    return null
  }
  val output = response.body<ComponentBean>()
  return output
}
