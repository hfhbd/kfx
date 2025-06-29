package com.jira.client

import com.jira.TerminologyResponseBean
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.`get`
import kotlin.Unit

/**
 * Get all defined names for 'epic' and 'sprint'
 * Returns a list of all defined names for the default words 'epic' and 'sprint'
 */
public suspend fun HttpClient.getAllTerminologyEntries(builder: suspend HttpRequestBuilder.() -> Unit = {}): TerminologyResponseBean {
  val response = `get`(urlString = """api/2/terminology/entries""") {
    builder()
  }
  val output = response.body<TerminologyResponseBean>()
  return output
}
