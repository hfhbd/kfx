package com.jira.client

import io.ktor.client.HttpClient
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.delete
import kotlin.String
import kotlin.Unit

/**
 * Delete property from project
 * Removes the property from the project identified by the key or by the id.
 */
public suspend fun HttpClient.deleteProperty_5(
  propertyKey: String,
  projectIdOrKey: String,
  builder: suspend HttpRequestBuilder.() -> Unit = {},
) {
  val response = delete(urlString = """api/2/project/${projectIdOrKey}/properties/${propertyKey}""") {
    builder()
  }
}
