package com.jira.client

import com.jira.WorkflowSchemeBean
import io.ktor.client.HttpClient
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType.Application.Json
import io.ktor.http.contentType
import kotlin.Unit

/**
 * Create a new workflow scheme
 * Create a new workflow scheme. The body contains a representation of the new scheme. Values not passed are assumed to be set to their defaults.
 */
public suspend fun HttpClient.createScheme(input: WorkflowSchemeBean, builder: suspend HttpRequestBuilder.() -> Unit = {}) {
  val response = post(urlString = """api/2/workflowscheme""") {
    contentType(Json)
    setBody(body = input)
    builder()
  }
}
