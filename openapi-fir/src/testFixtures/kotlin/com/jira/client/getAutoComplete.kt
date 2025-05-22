package com.jira.client

import com.jira.AutoCompleteResponseBean
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.`get`
import kotlin.Unit

/**
 * Get auto complete data for JQL searches
 * Returns the auto complete data required for JQL searches
 */
public suspend fun HttpClient.getAutoComplete(builder: suspend HttpRequestBuilder.() -> Unit = {}): AutoCompleteResponseBean {
  val response = `get`(urlString = """api/2/jql/autocompletedata""") {
    builder()
  }
  val output = response.body<AutoCompleteResponseBean>()
  return output
}
