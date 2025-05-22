package com.jira.client

import com.jira.IssueLinkTypeJsonBean
import io.ktor.client.HttpClient
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.put
import io.ktor.client.request.setBody
import io.ktor.http.ContentType.Application.Json
import io.ktor.http.contentType
import kotlin.String
import kotlin.Unit

/**
 * Update the specified issue link type
 * Update the specified issue link type.
 * @param issueLinkTypeId The issue link type id.
 */
public suspend fun HttpClient.updateIssueLinkType(
  input: IssueLinkTypeJsonBean,
  issueLinkTypeId: String,
  builder: suspend HttpRequestBuilder.() -> Unit = {},
) {
  val response = put(urlString = """api/2/issueLinkType/${issueLinkTypeId}""") {
    contentType(Json)
    setBody(body = input)
    builder()
  }
}
