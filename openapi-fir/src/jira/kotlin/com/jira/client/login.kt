package com.jira.client

import com.jira.AuthParams
import com.jira.AuthSuccess
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType.Application.Json
import io.ktor.http.contentType
import kotlin.Unit

/**
 * Create new user session
 * Creates a new session for a user in Jira. Once a session has been successfully created it can be used to access any of Jira's remote APIs and also the web UI by passing the appropriate HTTP Cookie header. Note that it is generally preferrable to use HTTP BASIC authentication with the REST API. However, this resource may be used to mimic the behaviour of Jira's log-in page (e.g. to display log-in errors to a user).
 */
public suspend fun HttpClient.login(input: AuthParams, builder: suspend HttpRequestBuilder.() -> Unit = {}): AuthSuccess {
  val response = post(urlString = """auth/1/session""") {
    contentType(Json)
    setBody(body = input)
    builder()
  }
  val output = response.body<AuthSuccess>()
  return output
}
