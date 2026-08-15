package com.jira.client

import com.jira.ComponentBean
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.`get`
import io.ktor.http.HttpStatusCode.Companion.NotFound
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
  when {
    response.status == NotFound -> {
      return null
    }
  }
  val output = response.body<ComponentBean>()
  return output
}
