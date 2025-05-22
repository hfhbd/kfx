package com.jira.client

import com.jira.IssueLinkTypeJsonBean
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.`get`
import kotlin.String
import kotlin.Unit

/**
 * Get information about an issue link type
 * Returns for a given issue link type id all information about this issue link type.
 *
 * @param issueLinkTypeId The issue link type id.
 */
public suspend fun HttpClient.getIssueLinkType(issueLinkTypeId: String, builder: suspend HttpRequestBuilder.() -> Unit = {}): IssueLinkTypeJsonBean? {
  val response = `get`(urlString = """api/2/issueLinkType/${issueLinkTypeId}""") {
    builder()
  }
  if (response.status.value == 404) {
    return null
  }
  val output = response.body<IssueLinkTypeJsonBean>()
  return output
}
