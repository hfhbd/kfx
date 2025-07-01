package com.jira.client

import com.jira.IssueRankRequestBean
import io.ktor.client.HttpClient
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.put
import io.ktor.client.request.setBody
import io.ktor.http.ContentType.Application.Json
import io.ktor.http.contentType
import kotlin.Unit

/**
 * Rank issues before or after a given issue
 * Moves (ranks) issues before or after a given issue. At most 50 issues may be ranked at once. This operation may fail for some issues, although this will be rare. In that case the 207 status code is returned for the whole response and detailed information regarding each issue is available in the response body. If rankCustomFieldId is not defined, the default rank field will be used.
 */
public suspend fun HttpClient.rankIssues(input: IssueRankRequestBean, builder: suspend HttpRequestBuilder.() -> Unit = {}) {
  val response = put(urlString = """agile/1.0/issue/rank""") {
    contentType(Json)
    setBody(body = input)
    builder()
  }
}
