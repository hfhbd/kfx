package com.jira.client

import com.jira.GroupBean
import com.jira.UpdateUserToGroupBean
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.parameter
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType.Application.Json
import io.ktor.http.contentType
import kotlin.String
import kotlin.Unit

/**
 * Add a user to a specified group
 * Adds given user to a group
 * @param groupname A name of requested group.
 */
public suspend fun HttpClient.addUserToGroup(
  input: UpdateUserToGroupBean,
  groupname: String? = null,
  builder: suspend HttpRequestBuilder.() -> Unit = {},
): GroupBean? {
  val response = post(urlString = """api/2/group/user""") {
    parameter("groupname", groupname)
    contentType(Json)
    setBody(body = input)
    builder()
  }
  if (response.status.value == 404) {
    return null
  }
  val output = response.body<GroupBean>()
  return output
}
