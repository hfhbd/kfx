package com.jira.client

import io.ktor.client.HttpClient
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.delete
import io.ktor.client.request.parameter
import kotlin.Long
import kotlin.Unit

/**
 * Deletes a role
 * Deletes a role. May return 403 in the future
 */
public suspend fun HttpClient.deleteProjectRole(
  id: Long,
  swap: Long? = null,
  builder: suspend HttpRequestBuilder.() -> Unit = {},
) {
  val response = delete(urlString = """api/2/role/${id}""") {
    parameter("swap", swap)
    builder()
  }
}
