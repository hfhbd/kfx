package com.jira.client

import com.jira.SearchRequestBean
import com.jira.SearchResultsBean
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType.Application.Json
import io.ktor.http.contentType
import kotlin.Unit

/**
 * Perform search with JQL
 * Performs a search using JQL.
 */
public suspend fun HttpClient.searchUsingSearchRequest(input: SearchRequestBean, builder: suspend HttpRequestBuilder.() -> Unit = {}): SearchResultsBean {
  val response = post(urlString = """api/2/search""") {
    contentType(Json)
    setBody(body = input)
    builder()
  }
  val output = response.body<SearchResultsBean>()
  return output
}
