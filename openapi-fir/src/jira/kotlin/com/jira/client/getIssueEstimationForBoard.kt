package com.jira.client

import com.jira.FieldValueBean
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.`get`
import io.ktor.client.request.parameter
import kotlin.Long
import kotlin.String
import kotlin.Unit

/**
 * Get the estimation of an issue for a board
 * Returns the estimation of the issue and a fieldId of the field that is used for it.
 * Original time internally stores and returns the estimation as a number of seconds.
 * The field used for estimation on the given board can be obtained from board configuration resource.
 * More information about the field are returned by edit meta resource or field resource.
 */
public suspend fun HttpClient.getIssueEstimationForBoard(
  issueIdOrKey: String,
  boardId: Long? = null,
  builder: suspend HttpRequestBuilder.() -> Unit = {},
): FieldValueBean? {
  val response = `get`(urlString = """agile/1.0/issue/${issueIdOrKey}/estimation""") {
    parameter("boardId", boardId)
    builder()
  }
  if (response.status.value == 404) {
    return null
  }
  val output = response.body<FieldValueBean>()
  return output
}
