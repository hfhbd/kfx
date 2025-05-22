package com.jira.client

import io.ktor.client.HttpClient
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.`get`
import kotlin.Unit

/**
 * Get email types for templates
 * Returns a list of root templates mapped with Event Types. The list can be used to decide which test emails to send.
 */
public suspend fun HttpClient.getEmailTypes(builder: suspend HttpRequestBuilder.() -> Unit = {}) {
  val response = `get`(urlString = """api/2/email-templates/types""") {
    builder()
  }
}
