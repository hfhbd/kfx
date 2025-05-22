package com.jira.client

import com.jira.AvatarBean
import io.ktor.client.HttpClient
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.put
import io.ktor.client.request.setBody
import io.ktor.http.ContentType.Application.Json
import io.ktor.http.contentType
import kotlin.String
import kotlin.Unit

/**
 * Update project avatar
 * Updates an avatar for a project. This is step 3/3 of changing an avatar for a project.
 */
public suspend fun HttpClient.updateProjectAvatar(
  input: AvatarBean,
  projectIdOrKey: String,
  builder: suspend HttpRequestBuilder.() -> Unit = {},
) {
  val response = put(urlString = """api/2/project/${projectIdOrKey}/avatar""") {
    contentType(Json)
    setBody(body = input)
    builder()
  }
}
