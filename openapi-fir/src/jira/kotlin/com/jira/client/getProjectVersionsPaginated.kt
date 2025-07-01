package com.jira.client

import com.jira.PageBean
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
 * Get paginated project versions
 * Returns all versions for the specified project. Results are paginated. Results can be ordered by the following fields: sequence, name, startDate, releaseDate.
 */
public suspend fun HttpClient.getProjectVersionsPaginated(
  projectIdOrKey: String,
  expand: String? = null,
  maxResults: Int? = null,
  orderBy: String? = null,
  startAt: Long? = null,
  builder: suspend HttpRequestBuilder.() -> Unit = {},
): PageBean? {
  val response = `get`(urlString = """api/2/project/${projectIdOrKey}/version""") {
    parameter("expand", expand)
    parameter("maxResults", maxResults)
    parameter("orderBy", orderBy)
    parameter("startAt", startAt)
    builder()
  }
  if (response.status.value == 404) {
    return null
  }
  val output = response.body<PageBean>()
  return output
}
