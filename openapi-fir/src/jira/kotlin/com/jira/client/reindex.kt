package com.jira.client

import com.jira.ReindexBean
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.parameter
import io.ktor.client.request.post
import kotlin.Boolean
import kotlin.String
import kotlin.Unit

/**
 * Start a reindex operation
 * Kicks off a reindex. Need Admin permissions to perform this reindex.
 */
public suspend fun HttpClient.reindex(
  indexChangeHistory: Boolean? = false,
  type: String? = null,
  indexWorklogs: Boolean? = false,
  indexComments: Boolean? = false,
  builder: suspend HttpRequestBuilder.() -> Unit = {},
): ReindexBean {
  val response = post(urlString = """api/2/reindex""") {
    parameter("indexChangeHistory", indexChangeHistory)
    parameter("type", type)
    parameter("indexWorklogs", indexWorklogs)
    parameter("indexComments", indexComments)
    builder()
  }
  val output = response.body<ReindexBean>()
  return output
}
