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
 * Set value of specified project's property
 * Sets the value of the specified project's property. You can use this resource to store a custom data against the project identified by the key or by the id. The user who stores the data is required to have permissions to administer the project.
 */
public suspend fun HttpClient.setProperty_4(
  input: PropertyBean,
  propertyKey: String,
  projectIdOrKey: String,
  builder: suspend HttpRequestBuilder.() -> Unit = {},
) {
  val response = put(urlString = """api/2/project/${projectIdOrKey}/properties/${propertyKey}""") {
    contentType(Json)
    setBody(body = input)
    builder()
  }
}
