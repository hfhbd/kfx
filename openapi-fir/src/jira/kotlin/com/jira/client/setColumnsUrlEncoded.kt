package com.jira.client

import io.ktor.client.HttpClient
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.put
import io.ktor.http.ContentType.Application.FormUrlEncoded
import io.ktor.http.contentType
import kotlin.Unit

/**
 * Set default columns for user
 * Sets the default columns for the given user. Admin permission will be required to get columns for a user other than the currently logged in user.
 */
public suspend fun HttpClient.setColumnsUrlEncoded(builder: suspend HttpRequestBuilder.() -> Unit = {}) {
  val response = put(urlString = """api/2/user/columns""") {
    contentType(FormUrlEncoded)
    builder()
  }
}
