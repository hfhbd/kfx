package com.jira.client

import com.jira.ProjectCategoryJsonBean
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.`get`
import kotlin.Unit

/**
 * Get all project categories
 * Returns all project categories
 */
public suspend fun HttpClient.getAllProjectCategories(builder: suspend HttpRequestBuilder.() -> Unit = {}): ProjectCategoryJsonBean {
  val response = `get`(urlString = """api/2/projectCategory""") {
    builder()
  }
  val output = response.body<ProjectCategoryJsonBean>()
  return output
}
