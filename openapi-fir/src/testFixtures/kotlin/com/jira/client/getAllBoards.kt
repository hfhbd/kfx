package com.jira.client

import com.jira.BoardBean
import com.jira.StringList
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
 * Get all boards
 * Returns all boards. This only includes boards that the user has permission to view.
 */
public suspend fun HttpClient.getAllBoards(
  maxResults: Int? = null,
  name: String? = null,
  projectKeyOrId: String? = null,
  type: StringList? = null,
  startAt: Long? = null,
  builder: suspend HttpRequestBuilder.() -> Unit = {},
): BoardBean {
  val response = `get`(urlString = """agile/1.0/board""") {
    parameter("maxResults", maxResults)
    parameter("name", name)
    parameter("projectKeyOrId", projectKeyOrId)
    parameter("type", type)
    parameter("startAt", startAt)
    builder()
  }
  val output = response.body<BoardBean>()
  return output
}
