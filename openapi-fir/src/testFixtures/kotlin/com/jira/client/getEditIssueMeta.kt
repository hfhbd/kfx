package com.jira.client

import com.jira.EditMetaBean
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.`get`
import kotlin.String
import kotlin.Unit

/**
 * Get metadata for issue types used for editing issues
 * Returns the meta data for editing an issue. The fields in the editmeta correspond to the fields in the edit screen for the issue. Fields not in the screen will not be in the editmeta.
 *
 * @param issueIdOrKey Issue id or key
 */
public suspend fun HttpClient.getEditIssueMeta(issueIdOrKey: String, builder: suspend HttpRequestBuilder.() -> Unit = {}): EditMetaBean? {
  val response = `get`(urlString = """api/2/issue/${issueIdOrKey}/editmeta""") {
    builder()
  }
  if (response.status.value == 404) {
    return null
  }
  val output = response.body<EditMetaBean>()
  return output
}
