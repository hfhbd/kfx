package com.jira.client

import com.jira.UserBean
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.`get`
import io.ktor.client.request.parameter
import kotlin.Boolean
import kotlin.Unit

/**
 * Get duplicated users count
 * Returns a list of users that match the search string. This resource cannot be accessed anonymously.
 * Duplicated means that the user has an account in more than one directory
 * and either more than one account is active or the only active account does not belong to the directory
 * with the highest priority.
 * The data returned by this endpoint is cached for 10 minutes and the cache is flushed when any User Directory
 * is added, removed, enabled, disabled, or synchronized.
 * A System Administrator can also flush the cache manually.
 * Related JAC ticket: https://jira.atlassian.com/browse/JRASERVER-68797
 */
public suspend fun HttpClient.getDuplicatedUsersCount(flush: Boolean? = null, builder: suspend HttpRequestBuilder.() -> Unit = {}): UserBean? {
  val response = `get`(urlString = """api/2/user/duplicated/count""") {
    parameter("flush", flush)
    builder()
  }
  if (response.status.value == 404) {
    return null
  }
  val output = response.body<UserBean>()
  return output
}
