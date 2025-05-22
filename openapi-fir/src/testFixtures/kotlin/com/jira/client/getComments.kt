package com.jira.client

import com.jira.CommentsWithPaginationJsonBean
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.`get`
import io.ktor.client.request.parameter
import kotlin.String
import kotlin.Unit

/**
 * Get comments for an issue
 * Returns all comments for an issue. Results can be ordered by the 'created' field which means the date a comment was added.
 *
 * @param issueIdOrKey Issue id or key
 * @param expand Optional flags: renderedBody (provides body rendered in HTML)
 * @param maxResults How many results on the page should be included. Defaults to 50.
 * @param orderBy Ordering of the results
 * @param startAt The page offset, if not specified then defaults to 0
 */
public suspend fun HttpClient.getComments(
  issueIdOrKey: String,
  expand: String? = null,
  maxResults: String? = null,
  orderBy: String? = null,
  startAt: String? = null,
  builder: suspend HttpRequestBuilder.() -> Unit = {},
): CommentsWithPaginationJsonBean? {
  val response = `get`(urlString = """api/2/issue/${issueIdOrKey}/comment""") {
    parameter("expand", expand)
    parameter("maxResults", maxResults)
    parameter("orderBy", orderBy)
    parameter("startAt", startAt)
    builder()
  }
  if (response.status.value == 404) {
    return null
  }
  val output = response.body<CommentsWithPaginationJsonBean>()
  return output
}
