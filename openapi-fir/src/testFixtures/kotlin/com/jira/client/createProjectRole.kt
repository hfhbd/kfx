package com.jira.client

import com.jira.CreateUpdateRoleRequestBean
import com.jira.ProjectRoleBean
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType.Application.Json
import io.ktor.http.contentType
import kotlin.Unit

/**
 * Create a new project role
 * Creates a new ProjectRole to be available in Jira. The created role does not have any default actors assigned.
 */
public suspend fun HttpClient.createProjectRole(input: CreateUpdateRoleRequestBean, builder: suspend HttpRequestBuilder.() -> Unit = {}): ProjectRoleBean {
  val response = post(urlString = """api/2/role""") {
    contentType(Json)
    setBody(body = input)
    builder()
  }
  val output = response.body<ProjectRoleBean>()
  return output
}
