package com.jira.client

import io.ktor.client.HttpClient
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.delete
import io.ktor.client.request.parameter
import kotlin.String
import kotlin.Unit

/**
 * Remove a user from a specified group
 * Removes given user from a group
 *
 * @param groupname A name of requested group.
 * @param username User to remove from a group
 */
public suspend fun HttpClient.removeUserFromGroup(
  groupname: String? = null,
  username: String? = null,
  builder: suspend HttpRequestBuilder.() -> Unit = {},
) {
  val response = delete(urlString = """api/2/group/user""") {
    parameter("groupname", groupname)
    parameter("username", username)
    builder()
  }
}
