package com.jira.client

import com.jira.CustomFieldDefinitionJsonBean
import com.jira.FieldBean
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType.Application.Json
import io.ktor.http.contentType
import kotlin.Unit

/**
 * Create a custom field using a definition
 * Creates a custom field using a definition
 */
public suspend fun HttpClient.createCustomField(input: CustomFieldDefinitionJsonBean, builder: suspend HttpRequestBuilder.() -> Unit = {}): FieldBean {
  val response = post(urlString = """api/2/field""") {
    contentType(Json)
    setBody(body = input)
    builder()
  }
  val output = response.body<FieldBean>()
  return output
}
