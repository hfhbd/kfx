package com.jira.client

import io.ktor.client.HttpClient
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.put
import kotlin.String
import kotlin.Unit

/**
 * Restore an archived project
 * Restores an archived project. In case of success restored project should be re-indexed.
 */
public suspend fun HttpClient.restoreProject(projectIdOrKey: String, builder: suspend HttpRequestBuilder.() -> Unit = {}) {
  val response = put(urlString = """api/2/project/${projectIdOrKey}/restore""") {
    builder()
  }
}
