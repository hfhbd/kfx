package com.jira.client

import com.jira.SecuritySchemeJsonBean
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.`get`
import kotlin.String
import kotlin.Unit

/**
 * Get issue security scheme for project
 * Returns the issue security scheme for project.
 */
public suspend fun HttpClient.getIssueSecurityScheme_1(projectKeyOrId: String, builder: suspend HttpRequestBuilder.() -> Unit = {}): SecuritySchemeJsonBean? {
  val response = `get`(urlString = """api/2/project/${projectKeyOrId}/issuesecuritylevelscheme""") {
    builder()
  }
  if (response.status.value == 404) {
    return null
  }
  val output = response.body<SecuritySchemeJsonBean>()
  return output
}
