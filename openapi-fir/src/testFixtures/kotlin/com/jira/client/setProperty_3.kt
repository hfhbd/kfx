package com.jira.client

import com.jira.PropertyBean
import io.ktor.client.HttpClient
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.put
import io.ktor.client.request.setBody
import io.ktor.http.ContentType.Application.Json
import io.ktor.http.contentType
import kotlin.String
import kotlin.Unit

/**
 * Update specified issue type's property
 * Sets the value of the specified issue type's property
 * @param propertyKey The key of the issue type's property. The maximum length of the key is 255 bytes
 * @param issueTypeId The issue type on which the property will be set.
 */
public suspend fun HttpClient.setProperty_3(
  input: PropertyBean,
  propertyKey: String,
  issueTypeId: String,
  builder: suspend HttpRequestBuilder.() -> Unit = {},
) {
  val response = put(urlString = """api/2/issuetype/${issueTypeId}/properties/${propertyKey}""") {
    contentType(Json)
    setBody(body = input)
    builder()
  }
}
