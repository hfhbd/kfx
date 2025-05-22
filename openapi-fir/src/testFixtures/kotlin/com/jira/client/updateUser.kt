package com.jira.client

import com.jira.UserWriteBean
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.put
import io.ktor.client.request.setBody
import io.ktor.http.ContentType.Application.Json
import io.ktor.http.contentType
import kotlin.Unit

/**
 * Update currently logged user
 * Modify currently logged user. The 'value' fields present will override the existing value. Fields skipped in request will not be changed. Only email and display name can be change that way. Requires user password.
 */
public suspend fun HttpClient.updateUser(input: UserWriteBean, builder: suspend HttpRequestBuilder.() -> Unit = {}): UserWriteBean? {
  val response = put(urlString = """api/2/myself""") {
    contentType(Json)
    setBody(body = input)
    builder()
  }
  if (response.status.value == 404) {
    return null
  }
  val output = response.body<UserWriteBean>()
  return output
}
