package com.jira.client

import com.jira.PageBean
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
 * Get paginated notification schemes
 * Returns a paginated list of notification schemes. In order to access notification scheme, the calling user is
 * required to have permissions to administer at least one project associated with the requested notification scheme. Each scheme contains
 * a list of events and recipient configured to receive notifications for these events. Consumer should allow events without recipients to appear in response.
 * The list is ordered by the scheme's name.
 * Follow the documentation of /notificationscheme/{id} resource for all details about returned value.
 */
public suspend fun HttpClient.getNotificationSchemes(
  expand: String? = null,
  maxResults: Int? = null,
  startAt: Long? = null,
  builder: suspend HttpRequestBuilder.() -> Unit = {},
): PageBean {
  val response = `get`(urlString = """api/2/notificationscheme""") {
    parameter("expand", expand)
    parameter("maxResults", maxResults)
    parameter("startAt", startAt)
    builder()
  }
  val output = response.body<PageBean>()
  return output
}
