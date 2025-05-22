package com.jira.client

import com.jira.TerminologyResponseBean
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.`get`
import kotlin.String
import kotlin.Unit

/**
 * Get epic or sprint name by original name
 * Returns epic or sprint name as specified in the {originalName} path param
 */
public suspend fun HttpClient.getTerminologyEntry(originalName: String, builder: suspend HttpRequestBuilder.() -> Unit = {}): TerminologyResponseBean? {
  val response = `get`(urlString = """api/2/terminology/entries/${originalName}""") {
    builder()
  }
  if (response.status.value == 404) {
    return null
  }
  val output = response.body<TerminologyResponseBean>()
  return output
}
