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
 * Update application roles
 * Updates the ApplicationRoles with the passed data if the version hash is the same as the server. Only the groups and default groups setting of the role may be updated. Requests to change the key or the name of the role will be silently ignored. It is acceptable to pass only the roles that are updated as roles that are present in the server but not in data to update with, will not be deleted.
 */
public suspend fun HttpClient.putBulk(
  input: ApplicationRoleBean,
  If_Match: String? = null,
  builder: suspend HttpRequestBuilder.() -> Unit = {},
): ApplicationRoleBean? {
  val response = put(urlString = """api/2/applicationrole""") {
    `header`("If-Match", If_Match)
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
