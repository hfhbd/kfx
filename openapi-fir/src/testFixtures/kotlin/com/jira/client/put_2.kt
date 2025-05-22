package com.jira.client

import com.jira.ApplicationRoleBean
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.`header`
import io.ktor.client.request.put
import io.ktor.client.request.setBody
import io.ktor.http.ContentType.Application.Json
import io.ktor.http.contentType
import kotlin.String
import kotlin.Unit

/**
 * Update application role
 * Updates the ApplicationRole with the passed data. Only the groups and default groups setting of the role may be updated. Requests to change the key or the name of the role will be silently ignored.
 * @param key the key of the role to update.
 * @param versionHash the hash of the version to update. Optional Param
 */
public suspend fun HttpClient.put_2(
  input: ApplicationRoleBean,
  key: String,
  If_Match: String? = null,
  versionHash: String? = null,
  builder: suspend HttpRequestBuilder.() -> Unit = {},
): ApplicationRoleBean? {
  val response = put(urlString = """api/2/applicationrole/${key}""") {
    `header`("If-Match", If_Match)
    `header`("versionHash", versionHash)
    contentType(Json)
    setBody(body = input)
    builder()
  }
  if (response.status.value == 404) {
    return null
  }
  val output = response.body<ApplicationRoleBean>()
  return output
}
