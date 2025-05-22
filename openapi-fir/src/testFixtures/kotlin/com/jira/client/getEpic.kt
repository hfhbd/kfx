package com.jira.client

import com.jira.EpicBean
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.`get`
import kotlin.String
import kotlin.Unit

/**
 * Get an epic by id or key
 * Returns the epic for a given epic Id. This epic will only be returned if the user has permission to view it.
 */
public suspend fun HttpClient.getEpic(epicIdOrKey: String, builder: suspend HttpRequestBuilder.() -> Unit = {}): EpicBean? {
  val response = `get`(urlString = """agile/1.0/epic/${epicIdOrKey}""") {
    builder()
  }
  if (response.status.value == 404) {
    return null
  }
  val output = response.body<EpicBean>()
  return output
}
