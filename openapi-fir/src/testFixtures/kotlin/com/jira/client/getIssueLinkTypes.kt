package com.jira.client

import com.jira.IssueLinkTypesBean
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.`get`
import kotlin.Unit

/**
 * Get list of available issue link types
 * Returns a list of available issue link types, if issue linking is enabled.
 */
public suspend fun HttpClient.getIssueLinkTypes(builder: suspend HttpRequestBuilder.() -> Unit = {}): IssueLinkTypesBean? {
  val response = `get`(urlString = """api/2/issueLinkType""") {
    builder()
  }
  if (response.status.value == 404) {
    return null
  }
  val output = response.body<IssueLinkTypesBean>()
  return output
}
