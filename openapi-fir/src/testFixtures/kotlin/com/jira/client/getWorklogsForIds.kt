package com.jira.client

import com.jira.Worklog
import com.jira.WorklogIdsRequestBean
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType.Application.Json
import io.ktor.http.contentType
import kotlin.Unit

/**
 * Returns worklogs for given ids.
 * Returns worklogs for given worklog ids. Only worklogs to which the calling user has permissions, will be included in the result. The returns set of worklogs is limited to 1000 elements.
 */
public suspend fun HttpClient.getWorklogsForIds(input: WorklogIdsRequestBean, builder: suspend HttpRequestBuilder.() -> Unit = {}): Worklog {
  val response = post(urlString = """api/2/worklog/list""") {
    contentType(Json)
    setBody(body = input)
    builder()
  }
  val output = response.body<Worklog>()
  return output
}
