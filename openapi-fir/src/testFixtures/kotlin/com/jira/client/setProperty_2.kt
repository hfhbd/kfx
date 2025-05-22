package com.jira.client

import io.ktor.client.HttpClient
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.put
import io.ktor.client.request.setBody
import io.ktor.http.ContentType.Application.Json
import io.ktor.http.contentType
import kotlin.String
import kotlin.Unit

/**
 * Update the value of a specific issue's property
 * Sets the value of the specified issue's property.
 * @param propertyKey The key of the issue's property
 * @param issueIdOrKey Issue id or key
 */
public suspend fun HttpClient.setProperty_2(
  input: String,
  propertyKey: String,
  issueIdOrKey: String,
  builder: suspend HttpRequestBuilder.() -> Unit = {},
) {
  val response = put(urlString = """api/2/issue/${issueIdOrKey}/properties/${propertyKey}""") {
    contentType(Json)
    setBody(body = input)
    builder()
  }
}
