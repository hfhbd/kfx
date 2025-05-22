package com.jira.client

import com.jira.SprintBean
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType.Application.Json
import io.ktor.http.contentType
import kotlin.Long
import kotlin.Unit

/**
 * Partially update a sprint
 * Performs a partial update of a sprint.
 * A partial update means that fields not present in the request JSON will not be updated.
 * Notes:
 * - Sprints that are in a closed state cannot be updated.
 * - A sprint can be started by updating the state to 'active'. This requires the sprint to be in the 'future' state and have a startDate and endDate set.
 * - A sprint can be completed by updating the state to 'closed'. This action requires the sprint to be in the 'active' state. This sets the completeDate to the time of the request.
 * - Other changes to state are not allowed.
 * - The completeDate field cannot be updated manually.
 * - Sprint goal can be removed by updating it's value to empty string
 * - Only Jira administrators can edit dates on sprints that are marked as synced.
 */
public suspend fun HttpClient.partiallyUpdateSprint(
  input: SprintBean,
  sprintId: Long,
  builder: suspend HttpRequestBuilder.() -> Unit = {},
): SprintBean? {
  val response = post(urlString = """agile/1.0/sprint/${sprintId}""") {
    contentType(Json)
    setBody(body = input)
    builder()
  }
  if (response.status.value == 404) {
    return null
  }
  val output = response.body<SprintBean>()
  return output
}
