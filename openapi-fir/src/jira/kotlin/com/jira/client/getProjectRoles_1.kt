package com.jira.client

import com.jira.ProjectRoleBean
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.`get`
import kotlin.Unit

/**
 * Get all project roles
 * Get all the ProjectRoles available in Jira. Currently this list is global.
 */
public suspend fun HttpClient.getProjectRoles_1(builder: suspend HttpRequestBuilder.() -> Unit = {}): ProjectRoleBean {
  val response = `get`(urlString = """api/2/role""") {
    builder()
  }
  val output = response.body<ProjectRoleBean>()
  return output
}
