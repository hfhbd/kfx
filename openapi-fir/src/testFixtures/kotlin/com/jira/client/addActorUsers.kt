package com.jira.client

import com.jira.ActorsMap
import com.jira.ProjectRoleBean
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType.Application.Json
import io.ktor.http.contentType
import kotlin.Long
import kotlin.String
import kotlin.Unit

/**
 * Add actor to project role
 * Adds an actor (user or group) to a project role. For user actors, their usernames should be used.
 */
public suspend fun HttpClient.addActorUsers(
  input: ActorsMap,
  projectIdOrKey: String,
  id: Long,
  builder: suspend HttpRequestBuilder.() -> Unit = {},
): ProjectRoleBean? {
  val response = post(urlString = """api/2/project/${projectIdOrKey}/role/${id}""") {
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
