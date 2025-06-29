package com.jira.client

import com.jira.UserWriteBean
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType.Application.Json
import io.ktor.http.contentType
import kotlin.Unit

/**
 * Create new user
 * Create user. By default created user will not be notified with email. If password field is not set then password will be randomly generated.
 */
public suspend fun HttpClient.createUser(input: UserWriteBean, builder: suspend HttpRequestBuilder.() -> Unit = {}): UserWriteBean {
  val response = post(urlString = """api/2/user""") {
    contentType(Json)
    setBody(body = input)
    builder()
  }
  val output = response.body<UserWriteBean>()
  return output
}
