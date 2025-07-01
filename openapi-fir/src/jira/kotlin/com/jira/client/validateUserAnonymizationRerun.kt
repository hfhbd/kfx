package com.jira.client

import com.jira.UserAnonymizationValidationBean
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.`get`
import io.ktor.client.request.parameter
import kotlin.String
import kotlin.Unit

/**
 * Get validation for user anonymization rerun
 * Validates user anonymization re-run process.
 */
public suspend fun HttpClient.validateUserAnonymizationRerun(
  expand: String? = null,
  oldUserKey: String? = null,
  oldUserName: String? = null,
  userKey: String? = null,
  builder: suspend HttpRequestBuilder.() -> Unit = {},
): UserAnonymizationValidationBean {
  val response = `get`(urlString = """api/2/user/anonymization/rerun""") {
    parameter("expand", expand)
    parameter("oldUserKey", oldUserKey)
    parameter("oldUserName", oldUserName)
    parameter("userKey", userKey)
    builder()
  }
  val output = response.body<UserAnonymizationValidationBean>()
  return output
}
