package com.jira.client

import io.ktor.client.HttpClient
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.delete
import kotlin.String
import kotlin.Unit

/**
 * Delete a project
 * Deletes a project
 */
public suspend fun HttpClient.deleteProject(projectIdOrKey: String, builder: suspend HttpRequestBuilder.() -> Unit = {}) {
  val response = delete(urlString = """api/2/project/${projectIdOrKey}""") {
    builder()
  }
}
