package com.jira.client

import io.ktor.client.HttpClient
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.post
import io.ktor.http.ContentType.Application.Zip
import io.ktor.http.contentType
import kotlin.Unit

/**
 * Update email templates with zip file
 * Extracts given zip file to temporary templates folder. If the folder already exists it will replace it's content
 */
public suspend fun HttpClient.uploadEmailTemplates(builder: suspend HttpRequestBuilder.() -> Unit = {}) {
  val response = post(urlString = """api/2/email-templates""") {
    contentType(Zip)
    builder()
  }
}
