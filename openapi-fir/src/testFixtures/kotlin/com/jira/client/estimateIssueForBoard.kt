package com.jira.client

import com.jira.FieldEditBean
import com.jira.FieldValueBean
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.parameter
import io.ktor.client.request.put
import io.ktor.client.request.setBody
import io.ktor.http.ContentType.Application.Json
import io.ktor.http.contentType
import kotlin.Long
import kotlin.String
import kotlin.Unit

/**
 * Update the estimation of an issue for a board
 * Updates the estimation of the issue. boardId param is required. This param determines which field will be updated on a issue.
 * Note that this resource changes the estimation field of the issue regardless of appearance the field on the screen.
 * Original time tracking estimation field accepts estimation in formats like "1w", "2d", "3h", "20m" or number which represent number of minutes.
 * However, internally the field stores and returns the estimation as a number of seconds.
 * The field used for estimation on the given board can be obtained from <a href="#agile/1.0/board-getConfiguration">board configuration resource</a>.
 * More information about the field are returned by edit meta resource or field resource.
 */
public suspend fun HttpClient.estimateIssueForBoard(
  input: FieldEditBean,
  issueIdOrKey: String,
  boardId: Long? = null,
  builder: suspend HttpRequestBuilder.() -> Unit = {},
): FieldValueBean? {
  val response = put(urlString = """agile/1.0/issue/${issueIdOrKey}/estimation""") {
    parameter("boardId", boardId)
    contentType(Json)
    setBody(body = input)
    builder()
  }
  if (response.status.value == 404) {
    return null
  }
  val output = response.body<FieldValueBean>()
  return output
}
