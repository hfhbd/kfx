package com.jira.client

import io.ktor.client.HttpClient
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.parameter
import io.ktor.client.request.post
import kotlin.String
import kotlin.Unit

/**
 * Add user to application
 * Add user to given application. Admin permission will be required to perform this operation.
 */
public suspend fun HttpClient.addUserToApplication_1(
  applicationKey: String? = null,
  username: String? = null,
  builder: suspend HttpRequestBuilder.() -> Unit = {},
) {
  val response = post(urlString = """api/2/user/application""") {
    parameter("applicationKey", applicationKey)
    parameter("username", username)
    builder()
  }
}
