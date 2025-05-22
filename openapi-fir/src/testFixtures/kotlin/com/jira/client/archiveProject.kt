package com.jira.client

import io.ktor.client.HttpClient
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.put
import kotlin.String
import kotlin.Unit

/**
 * Archive a project
 * Archives a project
 */
public suspend fun HttpClient.archiveProject(projectIdOrKey: String, builder: suspend HttpRequestBuilder.() -> Unit = {}) {
  val response = put(urlString = """api/2/project/${projectIdOrKey}/archive""") {
    builder()
  }
}
