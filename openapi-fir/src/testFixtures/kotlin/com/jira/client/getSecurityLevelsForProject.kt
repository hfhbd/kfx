package com.jira.client

import com.jira.SecurityListLevelJsonBean
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.`get`
import kotlin.String
import kotlin.Unit

/**
 * Get all security levels for project
 * Returns all security levels for the project that the current logged in user has access to. If the user does not have the Set Issue Security permission, the list will be empty.
 */
public suspend fun HttpClient.getSecurityLevelsForProject(projectKeyOrId: String, builder: suspend HttpRequestBuilder.() -> Unit = {}): SecurityListLevelJsonBean? {
  val response = `get`(urlString = """api/2/project/${projectKeyOrId}/securitylevel""") {
    builder()
  }
  if (response.status.value == 404) {
    return null
  }
  val output = response.body<SecurityListLevelJsonBean>()
  return output
}
