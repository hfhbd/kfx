package com.jira.client

import com.jira.LicenseValidationResults
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType.Application.Json
import io.ktor.http.contentType
import kotlin.String
import kotlin.Unit

/**
 * Validate a Jira license
 * Validates a Jira license
 */
public suspend fun HttpClient.validate(input: String, builder: suspend HttpRequestBuilder.() -> Unit = {}): LicenseValidationResults {
  val response = post(urlString = """api/2/licenseValidator""") {
    contentType(Json)
    setBody(body = input)
    builder()
  }
  val output = response.body<LicenseValidationResults>()
  return output
}
