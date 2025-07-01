package com.jira.client

import io.ktor.client.HttpClient
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.delete
import kotlin.String
import kotlin.Unit

/**
 * Delete the specified issue link type
 * Delete the specified issue link type.
 *
 * @param issueLinkTypeId The issue link type id.
 */
public suspend fun HttpClient.deleteIssueLinkType(issueLinkTypeId: String, builder: suspend HttpRequestBuilder.() -> Unit = {}) {
  val response = delete(urlString = """api/2/issueLinkType/${issueLinkTypeId}""") {
    builder()
  }
}
