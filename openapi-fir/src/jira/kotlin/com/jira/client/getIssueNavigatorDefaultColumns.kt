package com.jira.client

import com.jira.ColumnOptions
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.`get`
import kotlin.Unit

/**
 * Get default system columns for issue navigator
 * Returns the default system columns for issue navigator. Admin permission will be required.
 */
public suspend fun HttpClient.getIssueNavigatorDefaultColumns(builder: suspend HttpRequestBuilder.() -> Unit = {}): ColumnOptions {
  val response = `get`(urlString = """api/2/settings/columns""") {
    builder()
  }
  val output = response.body<ColumnOptions>()
  return output
}
