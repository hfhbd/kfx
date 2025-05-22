package com.jira.client

import io.ktor.client.HttpClient
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.`get`
import kotlin.Unit

/**
 * Get email templates as zip file
 * Creates a zip file containing email templates at local home and returns the file.
 */
public suspend fun HttpClient.downloadEmailTemplates(builder: suspend HttpRequestBuilder.() -> Unit = {}) {
  val response = `get`(urlString = """api/2/email-templates""") {
    builder()
  }
}
