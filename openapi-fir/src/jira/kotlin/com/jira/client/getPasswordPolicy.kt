package com.jira.client

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.`get`
import io.ktor.client.request.parameter
import kotlin.Boolean
import kotlin.String
import kotlin.Unit

/**
 * Get current password policy requirements
 * Returns the list of requirements for the current password policy. For example, "The password must have at least 10 characters.", "The password must not be similar to the user's name or email address.", etc.
 */
public suspend fun HttpClient.getPasswordPolicy(hasOldPassword: Boolean? = false, builder: suspend HttpRequestBuilder.() -> Unit = {}): String {
  val response = `get`(urlString = """api/2/password/policy""") {
    parameter("hasOldPassword", hasOldPassword)
    builder()
  }
  val output = response.body<String>()
  return output
}
