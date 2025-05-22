package com.jira.client

import com.jira.SecuritySchemeJsonBean
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.`get`
import kotlin.String
import kotlin.Unit

/**
 * Get specific issue security scheme by id
 * Returns the issue security scheme along with that are defined.
 *
 * @param id The issue security scheme id.
 */
public suspend fun HttpClient.getIssueSecurityScheme(id: String, builder: suspend HttpRequestBuilder.() -> Unit = {}): SecuritySchemeJsonBean {
  val response = `get`(urlString = """api/2/issuesecurityschemes/${id}""") {
    builder()
  }
  val output = response.body<SecuritySchemeJsonBean>()
  return output
}
