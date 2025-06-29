package com.jira.client

import com.jira.StatusCategoryJsonBean
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.`get`
import kotlin.String
import kotlin.Unit

/**
 * Get status category by ID or key
 * Returns a full representation of the StatusCategory having the given id or key
 */
public suspend fun HttpClient.getStatusCategory(idOrKey: String, builder: suspend HttpRequestBuilder.() -> Unit = {}): StatusCategoryJsonBean? {
  val response = `get`(urlString = """api/2/statuscategory/${idOrKey}""") {
    builder()
  }
  if (response.status.value == 404) {
    return null
  }
  val output = response.body<StatusCategoryJsonBean>()
  return output
}
