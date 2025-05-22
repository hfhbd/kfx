package com.jira.client

import io.ktor.client.HttpClient
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.post
import kotlin.String
import kotlin.Unit

/**
 * Add vote to issue
 * Adds voter (currently logged user) to particular ticket. You need to be logged in to use this method.
 *
 * @param issueIdOrKey Issue id.
 */
public suspend fun HttpClient.addVote(issueIdOrKey: String, builder: suspend HttpRequestBuilder.() -> Unit = {}) {
  val response = post(urlString = """api/2/issue/${issueIdOrKey}/votes""") {
    builder()
  }
}
