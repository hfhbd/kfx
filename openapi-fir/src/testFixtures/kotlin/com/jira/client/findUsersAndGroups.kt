package com.jira.client

import com.jira.UsersAndGroupsBean
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.`get`
import io.ktor.client.request.parameter
import kotlin.String
import kotlin.Unit

/**
 * Get users and groups matching query with highlighting
 * Returns a list of users and groups matching query with highlighting
 *
 * @param issueTypeId The list of issue type ids to further restrict the search
 * @param maxResults The maximum number of users to return
 * @param query A string used to search username, Name or e-mail address
 * @param showAvatar Show avatar
 * @param projectId The list of project ids to further restrict the search
 * @param fieldId The custom field id
 */
public suspend fun HttpClient.findUsersAndGroups(
  issueTypeId: String? = null,
  maxResults: String? = null,
  query: String? = null,
  showAvatar: String? = null,
  projectId: String? = null,
  fieldId: String? = null,
  builder: suspend HttpRequestBuilder.() -> Unit = {},
): UsersAndGroupsBean {
  val response = `get`(urlString = """api/2/groupuserpicker""") {
    parameter("issueTypeId", issueTypeId)
    parameter("maxResults", maxResults)
    parameter("query", query)
    parameter("showAvatar", showAvatar)
    parameter("projectId", projectId)
    parameter("fieldId", fieldId)
    builder()
  }
  val output = response.body<UsersAndGroupsBean>()
  return output
}
