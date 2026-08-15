package com.jira.client

import com.jira.CreateUpdateRoleRequestBean
import com.jira.ProjectRoleBean
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType.Application.Json
import io.ktor.http.HttpStatusCode.Companion.NotFound
import io.ktor.http.contentType
import kotlin.Long
import kotlin.Unit

/**
 * Partially updates a role's name or description
 * Partially updates a roles name or description.
 */
public suspend fun HttpClient.partialUpdateProjectRole(
  input: CreateUpdateRoleRequestBean,
  id: Long,
  builder: suspend HttpRequestBuilder.() -> Unit = {},
): ProjectRoleBean? {
  val response = post(urlString = """api/2/role/${id}""") {
    contentType(Json)
    setBody(input)
    builder()
  }
  when {
    response.status == NotFound -> {
      return null
    }
  }
  val output = response.body<ProjectRoleBean>()
  return output
}
