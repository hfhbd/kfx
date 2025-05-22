package com.jira.client

import io.ktor.client.HttpClient
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.post
import kotlin.Unit

/**
 * Update email templates to default
 * Replaces the current email templates pack with default templates, which are copied over from Jira binaries.
 */
public suspend fun HttpClient.revertEmailTemplatesToDefault(builder: suspend HttpRequestBuilder.() -> Unit = {}) {
  val response = post(urlString = """api/2/email-templates/revert""") {
    builder()
  }
}
