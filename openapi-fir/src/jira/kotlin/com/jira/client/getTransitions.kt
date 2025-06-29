package com.jira.client

import com.jira.TransitionsMetaBean
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.`get`
import io.ktor.client.request.parameter
import kotlin.String
import kotlin.Unit

/**
 * Get list of transitions possible for an issue
 * Get a list of the transitions possible for this issue by the current user, along with fields that are required and their types.
 * Fields will only be returned if `expand=transitions.fields`.
 * The fields in the metadata correspond to the fields in the transition screen for that transition.
 * Fields not in the screen will not be in the metadata.
 *
 * @param issueIdOrKey Issue id or key
 * @param transitionId Transition id
 */
public suspend fun HttpClient.getTransitions(
  issueIdOrKey: String,
  transitionId: String? = null,
  builder: suspend HttpRequestBuilder.() -> Unit = {},
): TransitionsMetaBean? {
  val response = `get`(urlString = """api/2/issue/${issueIdOrKey}/transitions""") {
    parameter("transitionId", transitionId)
    builder()
  }
  if (response.status.value == 404) {
    return null
  }
  val output = response.body<TransitionsMetaBean>()
  return output
}
