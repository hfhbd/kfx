package com.jira.client

import com.jira.VersionBean
import com.jira.VersionMoveBean
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType.Application.Json
import io.ktor.http.contentType
import kotlin.String
import kotlin.Unit

/**
 * Modify version's sequence
 * Modify a version's sequence within a project.
 * The move version bean has 2 alternative field value pairs:
 * - position: An absolute position, which may have a value of 'First', 'Last', 'Earlier' or 'Later'
 * - after: A version to place this version after.  The value should be the self link of another version
 */
public suspend fun HttpClient.moveVersion(
  input: VersionMoveBean,
  id: String,
  builder: suspend HttpRequestBuilder.() -> Unit = {},
): VersionBean? {
  val response = post(urlString = """api/2/version/${id}/move""") {
    contentType(Json)
    setBody(body = input)
    builder()
  }
  if (response.status.value == 404) {
    return null
  }
  val output = response.body<VersionBean>()
  return output
}
