package com.jira.client

import com.jira.SecuritySchemesJsonBean
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.`get`
import kotlin.Unit

/**
 * Get all issue security schemes
 * Returns all issue security schemes that are defined.
 */
public suspend fun HttpClient.getIssueSecuritySchemes(builder: suspend HttpRequestBuilder.() -> Unit = {}): SecuritySchemesJsonBean {
  val response = `get`(urlString = """api/2/issuesecurityschemes""") {
    builder()
  }
  val output = response.body<SecuritySchemesJsonBean>()
  return output
}
