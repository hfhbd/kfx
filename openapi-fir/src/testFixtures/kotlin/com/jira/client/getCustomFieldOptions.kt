package com.jira.client

import com.jira.CustomFieldOptionsBean
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.`get`
import io.ktor.client.request.parameter
import kotlin.String
import kotlin.Unit

/**
 * Get custom field options
 * Returns custom field's options defined in a given context composed of projects and issue types.
 *
 * @param customFieldId The ID of the custom field.
 * @param maxResults The maximum number of results to return.
 * @param issueTypeIds A list of issue type IDs in a context.
 * @param query A string used to filter options.
 * @param page The page of options to return.
 * @param projectIds A list of project IDs in a context.
 */
public suspend fun HttpClient.getCustomFieldOptions(
  customFieldId: String,
  maxResults: String? = null,
  issueTypeIds: String? = null,
  query: String? = null,
  page: String? = null,
  projectIds: String? = null,
  builder: suspend HttpRequestBuilder.() -> Unit = {},
): CustomFieldOptionsBean? {
  val response = `get`(urlString = """api/2/customFields/${customFieldId}/options""") {
    parameter("maxResults", maxResults)
    parameter("issueTypeIds", issueTypeIds)
    parameter("query", query)
    parameter("page", page)
    parameter("projectIds", projectIds)
    builder()
  }
  if (response.status.value == 404) {
    return null
  }
  val output = response.body<CustomFieldOptionsBean>()
  return output
}
