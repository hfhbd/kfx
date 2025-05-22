package com.jira.client

import com.jira.CustomFieldBean
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.`get`
import io.ktor.client.request.parameter
import kotlin.String
import kotlin.Unit

/**
 * Get custom fields with pagination
 * Returns a list of Custom Fields in the given range.
 *
 * @param sortColumn The column by which to sort the returned custom fields.
 * @param types A list of custom field types to filter the custom fields.
 * @param search A query string used to search custom fields.
 * @param maxResults The maximum number of custom fields to return.
 * @param sortOrder The order in which to sort the returned custom fields.
 * @param screenIds A list of screen IDs to filter the custom fields.
 * @param lastValueUpdate The last value update to filter the custom fields.
 * @param projectIds A list of project IDs to filter the custom fields.
 * @param startAt The starting index of the returned custom fields.
 */
public suspend fun HttpClient.getCustomFields(
  sortColumn: String? = null,
  types: String? = null,
  search: String? = null,
  maxResults: String? = null,
  sortOrder: String? = null,
  screenIds: String? = null,
  lastValueUpdate: String? = null,
  projectIds: String? = null,
  startAt: String? = null,
  builder: suspend HttpRequestBuilder.() -> Unit = {},
): CustomFieldBean? {
  val response = `get`(urlString = """api/2/customFields""") {
    parameter("sortColumn", sortColumn)
    parameter("types", types)
    parameter("search", search)
    parameter("maxResults", maxResults)
    parameter("sortOrder", sortOrder)
    parameter("screenIds", screenIds)
    parameter("lastValueUpdate", lastValueUpdate)
    parameter("projectIds", projectIds)
    parameter("startAt", startAt)
    builder()
  }
  if (response.status.value == 404) {
    return null
  }
  val output = response.body<CustomFieldBean>()
  return output
}
