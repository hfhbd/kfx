package com.jira.client

import com.jira.PermissionsJsonBean
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.`get`
import kotlin.Unit

/**
 * Get all permissions present in Jira instance
 * Returns all permissions that are present in the Jira instance - Global, Project and the global ones added by plugins
 */
public suspend fun HttpClient.getAllPermissions(builder: suspend HttpRequestBuilder.() -> Unit = {}): PermissionsJsonBean {
  val response = `get`(urlString = """api/2/permissions""") {
    builder()
  }
  val output = response.body<PermissionsJsonBean>()
  return output
}
