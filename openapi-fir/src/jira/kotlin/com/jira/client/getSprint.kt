package com.jira.client

import com.jira.SprintBean
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.`get`
import io.ktor.http.HttpStatusCode.Companion.NotFound
import kotlin.Long
import kotlin.Unit

/**
 * Get sprint by id
 * Returns a single sprint, for a given sprint Id. The sprint will only be returned if the user can view the board that the sprint was created on, or view at least one of the issues in the sprint.
 */
public suspend fun HttpClient.getSprint(sprintId: Long, builder: suspend HttpRequestBuilder.() -> Unit = {}): SprintBean? {
  val response = `get`(urlString = """agile/1.0/sprint/${sprintId}""") {
    builder()
  }
  when {
    response.status == NotFound -> {
      return null
    }
  }
  val output = response.body<SprintBean>()
  return output
}
