package com.jira.client

import io.ktor.client.HttpClient
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.delete
import io.ktor.client.request.parameter
import kotlin.String
import kotlin.Unit

/**
 * Delete a project component
 * Delete a project component.
 *
 * @param id The component to delete.
 * @param moveIssuesTo The new component applied to issues whose 'id' component will be deleted. If this value is null, then the 'id' component is simply removed from the related isues.
 */
public suspend fun HttpClient.delete(
  id: String,
  moveIssuesTo: String? = null,
  builder: suspend HttpRequestBuilder.() -> Unit = {},
) {
  val response = delete(urlString = """api/2/component/${id}""") {
    parameter("moveIssuesTo", moveIssuesTo)
    builder()
  }
}
