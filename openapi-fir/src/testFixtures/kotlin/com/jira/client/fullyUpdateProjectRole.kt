package com.jira.client

import com.jira.CreateUpdateRoleRequestBean
import com.jira.ProjectRoleBean
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
 * Fully updates a role's name and description
 * Fully updates a roles. Both name and description must be given.
 */
public suspend fun HttpClient.fullyUpdateProjectRole(
  input: CreateUpdateRoleRequestBean,
  id: Long,
  builder: suspend HttpRequestBuilder.() -> Unit = {},
): ProjectRoleBean? {
  val response = put(urlString = """api/2/role/${id}""") {
    contentType(Json)
    setBody(body = input)
    builder()
  }
  if (response.status.value == 404) {
    return null
  }
  val output = response.body<ProjectRoleBean>()
  return output
}
