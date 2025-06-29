package com.jira.client

import com.jira.EpicRankRequestBean
import io.ktor.client.HttpClient
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.put
import io.ktor.client.request.setBody
import io.ktor.http.ContentType.Application.Json
import io.ktor.http.contentType
import kotlin.String
import kotlin.Unit

/**
 * Rank an epic relative to another
 * Moves (ranks) an epic before or after a given epic. If rankCustomFieldId is not defined, the default rank field will be used.
 */
public suspend fun HttpClient.rankEpics(
  input: EpicRankRequestBean,
  epicIdOrKey: String,
  builder: suspend HttpRequestBuilder.() -> Unit = {},
) {
  val response = put(urlString = """agile/1.0/epic/${epicIdOrKey}/rank""") {
    contentType(Json)
    setBody(body = input)
    builder()
  }
}
