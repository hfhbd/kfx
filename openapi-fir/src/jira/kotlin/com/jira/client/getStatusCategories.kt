package com.jira.client

import com.jira.StatusCategoryJsonBean
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.`get`
import io.ktor.client.request.parameter
import io.ktor.http.HttpStatusCode.Companion.NotFound
import kotlin.String
import kotlin.Unit

/**
 * Get all status categories
 * Returns a list of all status categories
 *
 * @param request a Request
 * @param uriInfo a UriInfo
 */
public suspend fun HttpClient.getStatusCategories(
  request: String? = null,
  uriInfo: String? = null,
  builder: suspend HttpRequestBuilder.() -> Unit = {},
): StatusCategoryJsonBean? {
  val response = `get`(urlString = """api/2/statuscategory""") {
    parameter("request", request)
    parameter("uriInfo", uriInfo)
    builder()
  }
  when {
    response.status == NotFound -> {
      return null
    }
  }
  val output = response.body<StatusCategoryJsonBean>()
  return output
}
