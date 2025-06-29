package com.jira.client

import com.jira.IssueSubTaskMovePositionBean
import io.ktor.client.HttpClient
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType.Application.Json
import io.ktor.http.contentType
import kotlin.String
import kotlin.Unit

/**
 * Reorder an issue's subtasks
 * Reorders an issue's subtasks by moving the subtask at index 'from' to index 'to'.
 * @param issueIdOrKey The parent issue's key or id
 */
public suspend fun HttpClient.moveSubTasks(
  input: IssueSubTaskMovePositionBean,
  issueIdOrKey: String,
  builder: suspend HttpRequestBuilder.() -> Unit = {},
) {
  val response = post(urlString = """api/2/issue/${issueIdOrKey}/subtask/move""") {
    contentType(Json)
    setBody(body = input)
    builder()
  }
}
