package com.jira.client

import io.ktor.client.HttpClient
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.post
import kotlin.Unit

/**
 * Update email templates with previously uploaded pack
 * Replaces the current email templates pack with previously uploaded one, if exists.
 */
public suspend fun HttpClient.applyEmailTemplates(builder: suspend HttpRequestBuilder.() -> Unit = {}) {
  val response = post(urlString = """api/2/email-templates/apply""") {
    builder()
  }
}
