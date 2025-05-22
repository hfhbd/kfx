package com.jira.client

import com.jira.VersionBean
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.`get`
import io.ktor.client.request.parameter
import kotlin.Int
import kotlin.Long
import kotlin.String
import kotlin.Unit

/**
 * Get all versions from a board
 * Returns all versions from a board, for a given board Id. This only includes versions that the user has permission to view. Note, if the user does not have permission to view the board, no versions will be returned at all. Returned versions are ordered by the name of the project from which they belong and then by sequence defined by user.
 */
public suspend fun HttpClient.getAllVersions(
  boardId: Long,
  maxResults: Int? = null,
  released: String? = null,
  startAt: Long? = null,
  builder: suspend HttpRequestBuilder.() -> Unit = {},
): VersionBean? {
  val response = `get`(urlString = """agile/1.0/board/${boardId}/version""") {
    parameter("maxResults", maxResults)
    parameter("released", released)
    parameter("startAt", startAt)
    builder()
  }
  if (response.status.value == 404) {
    return null
  }
  val output = response.body<VersionBean>()
  return output
}
