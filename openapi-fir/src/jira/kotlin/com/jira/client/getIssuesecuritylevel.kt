package com.jira.client

import com.jira.SecurityLevelJsonBean
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.`get`
import kotlin.String
import kotlin.Unit

/**
 * Get a security level by ID
 * Returns a full representation of the security level that has the given id.
 */
public suspend fun HttpClient.getIssuesecuritylevel(id: String, builder: suspend HttpRequestBuilder.() -> Unit = {}): SecurityLevelJsonBean? {
  val response = `get`(urlString = """api/2/securitylevel/${id}""") {
    builder()
  }
  if (response.status.value == 404) {
    return null
  }
  val output = response.body<SecurityLevelJsonBean>()
  return output
}
