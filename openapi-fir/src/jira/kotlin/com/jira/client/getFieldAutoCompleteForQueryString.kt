package com.jira.client

import com.jira.AutoCompleteResultWrapper
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.`get`
import io.ktor.client.request.parameter
import kotlin.String
import kotlin.Unit

/**
 * Get auto complete suggestions for JQL search
 * Returns auto complete suggestions for JQL search
 */
public suspend fun HttpClient.getFieldAutoCompleteForQueryString(
  predicateValue: String? = null,
  predicateName: String? = null,
  fieldName: String? = null,
  fieldValue: String? = null,
  builder: suspend HttpRequestBuilder.() -> Unit = {},
): AutoCompleteResultWrapper {
  val response = `get`(urlString = """api/2/jql/autocompletedata/suggestions""") {
    parameter("predicateValue", predicateValue)
    parameter("predicateName", predicateName)
    parameter("fieldName", fieldName)
    parameter("fieldValue", fieldValue)
    builder()
  }
  val output = response.body<AutoCompleteResultWrapper>()
  return output
}
