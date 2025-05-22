package com.jira.client

import com.jira.FilterBean
import com.jira.StringList
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.`get`
import io.ktor.client.request.parameter
import kotlin.Unit

/**
 * Get favourite filters
 * Returns the favourite filters of the logged-in user
 */
public suspend fun HttpClient.getFavouriteFilters(expand: StringList? = null, builder: suspend HttpRequestBuilder.() -> Unit = {}): FilterBean {
  val response = `get`(urlString = """api/2/filter/favourite""") {
    parameter("expand", expand)
    builder()
  }
  val output = response.body<FilterBean>()
  return output
}
