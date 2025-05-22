package com.jira.client

import com.jira.Property
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.`get`
import io.ktor.client.request.parameter
import kotlin.String
import kotlin.Unit

/**
 * Get an application property by key
 * Returns an application property.
 *
 * @param permissionLevel when fetching a list specifies the permission level of all items in the list
 * see {@link com.atlassian.jira.bc.admin.ApplicationPropertiesService.EditPermissionLevel}
 * @param keyFilter when fetching a list allows the list to be filtered by the property's start of key
 * e.g. "jira.lf.*" whould fetch only those permissions that are editable and whose keys start with
 *      *                        "jira.lf.". This is a regex.
 * @param key a String containing the property key.
 */
public suspend fun HttpClient.getProperty_4(
  permissionLevel: String? = null,
  keyFilter: String? = null,
  key: String? = null,
  builder: suspend HttpRequestBuilder.() -> Unit = {},
): Property? {
  val response = `get`(urlString = """api/2/application-properties""") {
    parameter("permissionLevel", permissionLevel)
    parameter("keyFilter", keyFilter)
    parameter("key", key)
    builder()
  }
  if (response.status.value == 404) {
    return null
  }
  val output = response.body<Property>()
  return output
}
