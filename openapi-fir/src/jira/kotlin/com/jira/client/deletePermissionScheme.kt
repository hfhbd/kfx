package com.jira.client

import io.ktor.client.HttpClient
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.delete
import kotlin.Long
import kotlin.Unit

/**
 * Delete a permission scheme by ID
 * Deletes a permission scheme identified by the given id.
 */
public suspend fun HttpClient.deletePermissionScheme(schemeId: Long, builder: suspend HttpRequestBuilder.() -> Unit = {}) {
  val response = delete(urlString = """api/2/permissionscheme/${schemeId}""") {
    builder()
  }
}
