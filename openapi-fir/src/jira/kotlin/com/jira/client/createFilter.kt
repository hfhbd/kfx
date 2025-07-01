package com.jira.client

import com.jira.FilterBean
import com.jira.StringList
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.parameter
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType.Application.Json
import io.ktor.http.contentType
import kotlin.Unit

/**
 * Create a new filter
 * Creates a new filter, and returns newly created filter. Currently sets permissions just using the users default sharing permissions
 */
public suspend fun HttpClient.createFilter(
  input: FilterBean,
  expand: StringList? = null,
  builder: suspend HttpRequestBuilder.() -> Unit = {},
): FilterBean {
  val response = post(urlString = """api/2/filter""") {
    parameter("expand", expand)
    contentType(Json)
    setBody(body = input)
    builder()
  }
  val output = response.body<FilterBean>()
  return output
}
