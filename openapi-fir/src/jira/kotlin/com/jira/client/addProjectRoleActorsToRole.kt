package com.jira.client

import com.jira.ActorInputBean
import com.jira.ProjectRoleActorsBean
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType.Application.Json
import io.ktor.http.contentType
import kotlin.Long
import kotlin.Unit

/**
 * Adds default actors to a role
 * Adds default actors to the given role. The request data should contain a list of usernames or a list of groups to add.
 */
public suspend fun HttpClient.addProjectRoleActorsToRole(
  input: ActorInputBean,
  id: Long,
  builder: suspend HttpRequestBuilder.() -> Unit = {},
): ProjectRoleActorsBean? {
  val response = post(urlString = """api/2/role/${id}/actors""") {
    contentType(Json)
    setBody(body = input)
    builder()
  }
  if (response.status.value == 404) {
    return null
  }
  val output = response.body<ProjectRoleActorsBean>()
  return output
}
