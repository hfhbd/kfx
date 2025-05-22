package com.jira.client

import com.jira.ProjectIdentity
import com.jira.ProjectInputBean
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType.Application.Json
import io.ktor.http.contentType
import kotlin.Unit

/**
 * Create a new project
 * Creates a new project
 */
public suspend fun HttpClient.createProject(input: ProjectInputBean, builder: suspend HttpRequestBuilder.() -> Unit = {}): ProjectIdentity {
  val response = post(urlString = """api/2/project""") {
    contentType(Json)
    setBody(body = input)
    builder()
  }
  val output = response.body<ProjectIdentity>()
  return output
}
