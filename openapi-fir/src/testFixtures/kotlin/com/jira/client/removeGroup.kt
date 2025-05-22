package com.jira.client

import io.ktor.client.HttpClient
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.delete
import io.ktor.client.request.parameter
import kotlin.String
import kotlin.Unit

/**
 * Delete a specified group
 * Deletes a group by given group parameter
 *
 * @param groupname The name of the group to delete.
 * @param swapGroup A different group to transfer the restrictions to.
 */
public suspend fun HttpClient.removeGroup(
  groupname: String? = null,
  swapGroup: String? = null,
  builder: suspend HttpRequestBuilder.() -> Unit = {},
) {
  val response = delete(urlString = """api/2/group""") {
    parameter("groupname", groupname)
    parameter("swapGroup", swapGroup)
    builder()
  }
}
