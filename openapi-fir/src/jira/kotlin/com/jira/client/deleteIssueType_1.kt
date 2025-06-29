package com.jira.client

import io.ktor.client.HttpClient
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.delete
import kotlin.String
import kotlin.Unit

/**
 * Delete specified issue type and migrate associated issues
 * Deletes the specified issue type. If the issue type has any associated issues, these issues will be migrated to the alternative issue type specified in the parameter.
 *
 * @param id The issue type id.
 * @param alternativeIssueTypeId The id of an issue type to which issues associated with the removed issue type will be migrated.
 */
public suspend fun HttpClient.deleteIssueType_1(
  id: String,
  alternativeIssueTypeId: String,
  builder: suspend HttpRequestBuilder.() -> Unit = {},
) {
  val response = delete(urlString = """api/2/issuetype/${id}""") {
    builder()
  }
}
