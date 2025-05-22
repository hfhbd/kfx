package com.jira.client

import com.jira.NotificationSchemeBean
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.`get`
import io.ktor.client.request.parameter
import kotlin.String
import kotlin.Unit

/**
 * Get notification scheme associated with the project
 * Gets a notification scheme associated with the project. Follow the documentation of /notificationscheme/{id} resource for all details about returned value.
 */
public suspend fun HttpClient.getNotificationScheme_1(
  projectKeyOrId: String,
  expand: String? = null,
  builder: suspend HttpRequestBuilder.() -> Unit = {},
): NotificationSchemeBean? {
  val response = `get`(urlString = """api/2/project/${projectKeyOrId}/notificationscheme""") {
    parameter("expand", expand)
    builder()
  }
  if (response.status.value == 404) {
    return null
  }
  val output = response.body<NotificationSchemeBean>()
  return output
}
