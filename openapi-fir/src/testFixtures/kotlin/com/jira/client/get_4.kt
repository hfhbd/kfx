package com.jira.client

import com.jira.ApplicationRoleBean
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.`get`
import kotlin.String
import kotlin.Unit

/**
 * Get application role by key
 * Returns the ApplicationRole with passed key if it exists.
 *
 * @param key the key of the role to use.
 */
public suspend fun HttpClient.get_4(key: String, builder: suspend HttpRequestBuilder.() -> Unit = {}): ApplicationRoleBean? {
  val response = `get`(urlString = """api/2/applicationrole/${key}""") {
    builder()
  }
  if (response.status.value == 404) {
    return null
  }
  val output = response.body<ApplicationRoleBean>()
  return output
}
