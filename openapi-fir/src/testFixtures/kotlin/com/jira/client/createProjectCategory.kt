package com.jira.client

import com.jira.ProjectCategoryBean
import com.jira.ProjectCategoryJsonBean
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType.Application.Json
import io.ktor.http.contentType
import kotlin.Unit

/**
 * Create project category
 * Create a project category.
 */
public suspend fun HttpClient.createProjectCategory(input: ProjectCategoryBean, builder: suspend HttpRequestBuilder.() -> Unit = {}): ProjectCategoryJsonBean {
  val response = post(urlString = """api/2/projectCategory""") {
    contentType(Json)
    setBody(body = input)
    builder()
  }
  val output = response.body<ProjectCategoryJsonBean>()
  return output
}
