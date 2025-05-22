package com.jira.client

import com.jira.ApplicationRoleBean
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.`get`
import kotlin.Unit

/**
 * Get all application roles in the system
 * Returns all application roles in the system.
 */
public suspend fun HttpClient.getAll(builder: suspend HttpRequestBuilder.() -> Unit = {}): ApplicationRoleBean {
  val response = `get`(urlString = """api/2/applicationrole""") {
    builder()
  }
  val output = response.body<ApplicationRoleBean>()
  return output
}
