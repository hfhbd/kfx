package com.jira.client

import io.ktor.client.HttpClient
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.`get`
import kotlin.String
import kotlin.Unit

/**
 * Get all roles in project
 * Returns all roles in the given project Id or key, with links to full details on each role.
 */
public suspend fun HttpClient.getProjectRoles(projectIdOrKey: String, builder: suspend HttpRequestBuilder.() -> Unit = {}) {
  val response = `get`(urlString = """api/2/project/${projectIdOrKey}/role""") {
    builder()
  }
}
