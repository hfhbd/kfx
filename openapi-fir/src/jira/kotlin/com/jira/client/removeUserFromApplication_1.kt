package com.jira.client

import io.ktor.client.HttpClient
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.delete
import io.ktor.client.request.parameter
import kotlin.String
import kotlin.Unit

/**
 * Remove user from application
 * Remove user from given application. Admin permission will be required to perform this operation.
 */
public suspend fun HttpClient.removeUserFromApplication_1(
  applicationKey: String? = null,
  username: String? = null,
  builder: suspend HttpRequestBuilder.() -> Unit = {},
) {
  val response = delete(urlString = """api/2/user/application""") {
    parameter("applicationKey", applicationKey)
    parameter("username", username)
    builder()
  }
}
