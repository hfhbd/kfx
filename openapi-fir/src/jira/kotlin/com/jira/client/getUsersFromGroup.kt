package com.jira.client

import com.jira.UserJsonBean
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.`get`
import io.ktor.client.request.parameter
import kotlin.String
import kotlin.Unit

/**
 * Get users from a specified group
 * Returns a paginated list of users who are members of the specified group and its subgroups
 *
 * @param groupname The group name.
 * @param includeInactiveUsers Include inactive users.
 * @param maxResults The maximum number of users to return.
 * @param startAt The index of the first user in group to return.
 */
public suspend fun HttpClient.getUsersFromGroup(
  groupname: String,
  includeInactiveUsers: String? = null,
  maxResults: String? = null,
  startAt: String? = null,
  builder: suspend HttpRequestBuilder.() -> Unit = {},
): UserJsonBean? {
  val response = `get`(urlString = """api/2/group/member""") {
    parameter("includeInactiveUsers", includeInactiveUsers)
    parameter("maxResults", maxResults)
    parameter("startAt", startAt)
    builder()
  }
  if (response.status.value == 404) {
    return null
  }
  val output = response.body<UserJsonBean>()
  return output
}
