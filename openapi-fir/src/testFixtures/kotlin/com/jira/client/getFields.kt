package com.jira.client

import com.jira.FieldBean
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.`get`
import kotlin.Unit

/**
 * Get all fields, both System and Custom
 * Returns a list of all fields, both System and Custom
 */
public suspend fun HttpClient.getFields(builder: suspend HttpRequestBuilder.() -> Unit = {}): FieldBean {
  val response = `get`(urlString = """api/2/field""") {
    builder()
  }
  val output = response.body<FieldBean>()
  return output
}
