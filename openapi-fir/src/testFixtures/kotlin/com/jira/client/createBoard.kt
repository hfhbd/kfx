package com.jira.client

import com.jira.BoardBean
import com.jira.BoardCreateBean
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType.Application.Json
import io.ktor.http.contentType
import kotlin.Unit

/**
 * Create a new board
 * Creates a new board. Board name, type and filter Id is required.
 * - name - Must be less than 255 characters.
 * - type - Valid values: scrum, kanban
 * - filterId - Id of a filter that the user has permissions to view. Note, if the user does not have the 'Create shared objects' permission and tries to create a shared board, a private board will be created instead (remember that board sharing depends on the filter sharing).
 * Note:
 * - If you want to create a new project with an associated board, use the JIRA platform REST API. For more information, see the Create project method. The projectTypeKey for software boards must be 'software' and the projectTemplateKey must be either com.pyxis.greenhopper.jira:gh-kanban-template or com.pyxis.greenhopper.jira:gh-scrum-template.
 * - You can create a filter using the JIRA REST API. For more information, see the Create filter method.
 * - If you do not ORDER BY the Rank field for the filter of your board, you will not be able to reorder issues on the board.
 */
public suspend fun HttpClient.createBoard(input: BoardCreateBean, builder: suspend HttpRequestBuilder.() -> Unit = {}): BoardBean {
  val response = post(urlString = """agile/1.0/board""") {
    contentType(Json)
    setBody(body = input)
    builder()
  }
  val output = response.body<BoardBean>()
  return output
}
