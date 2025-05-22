package com.jira.client

import com.jira.ProjectCategoryBean
import com.jira.ProjectCategoryJsonBean
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.put
import io.ktor.client.request.setBody
import io.ktor.http.ContentType.Application.Json
import io.ktor.http.contentType
import kotlin.Long
import kotlin.Unit

/**
 * Update project category
 * Modify a project category.
 */
public suspend fun HttpClient.updateProjectCategory(
  input: ProjectCategoryBean,
  id: Long,
  builder: suspend HttpRequestBuilder.() -> Unit = {},
): ProjectCategoryJsonBean? {
  val response = put(urlString = """api/2/projectCategory/${id}""") {
    contentType(Json)
    setBody(body = input)
    builder()
  }
  if (response.status.value == 404) {
    return null
  }
  val output = response.body<ProjectCategoryJsonBean>()
  return output
}
