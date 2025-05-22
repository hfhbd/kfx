package com.jira.client

import com.jira.VoteBean
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.`get`
import kotlin.String
import kotlin.Unit

/**
 * Get votes for issue
 * A REST sub-resource representing the voters on the issue.
 *
 * @param issueIdOrKey Issue id or key
 */
public suspend fun HttpClient.getVotes(issueIdOrKey: String, builder: suspend HttpRequestBuilder.() -> Unit = {}): VoteBean? {
  val response = `get`(urlString = """api/2/issue/${issueIdOrKey}/votes""") {
    builder()
  }
  if (response.status.value == 404) {
    return null
  }
  val output = response.body<VoteBean>()
  return output
}
