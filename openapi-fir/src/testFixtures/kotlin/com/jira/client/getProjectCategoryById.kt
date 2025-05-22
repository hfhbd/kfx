package com.jira.client

import com.jira.ProjectCategoryJsonBean
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.`get`
import kotlin.Long
import kotlin.Unit

/**
 * Get project category by ID
 * Returns a full representation of the project category that has the given id.
 */
public suspend fun HttpClient.getProjectCategoryById(id: Long, builder: suspend HttpRequestBuilder.() -> Unit = {}): ProjectCategoryJsonBean? {
  val response = `get`(urlString = """api/2/projectCategory/${id}""") {
    builder()
  }
  if (response.status.value == 404) {
    return null
  }
  val output = response.body<ProjectCategoryJsonBean>()
  return output
}
