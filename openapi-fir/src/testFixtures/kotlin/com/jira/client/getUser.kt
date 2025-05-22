package com.jira.client

import com.jira.UserBean
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.`get`
import kotlin.Unit

/**
 * Get currently logged user
 * Returns currently logged user. This resource cannot be accessed anonymously
 */
public suspend fun HttpClient.getUser(builder: suspend HttpRequestBuilder.() -> Unit = {}): UserBean {
  val response = `get`(urlString = """api/2/myself""") {
    builder()
  }
  val output = response.body<UserBean>()
  return output
}
