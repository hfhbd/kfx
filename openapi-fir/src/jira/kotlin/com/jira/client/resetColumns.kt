package com.jira.client

import io.ktor.client.HttpClient
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.delete
import io.ktor.client.request.parameter
import kotlin.String
import kotlin.Unit

/**
 * Reset default columns to system default
 * Reset the default columns for the given user to the system default. Admin permission will be required to get columns for a user other than the currently logged in user.
 */
public suspend fun HttpClient.resetColumns(username: String? = null, builder: suspend HttpRequestBuilder.() -> Unit = {}) {
  val response = delete(urlString = """api/2/user/columns""") {
    parameter("username", username)
    builder()
  }
}
