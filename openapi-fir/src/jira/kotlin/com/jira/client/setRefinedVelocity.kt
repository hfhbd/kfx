package com.jira.client

import com.jira.BooleanSettingBean
import io.ktor.client.HttpClient
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.put
import io.ktor.client.request.setBody
import io.ktor.http.ContentType.Application.Json
import io.ktor.http.contentType
import kotlin.Long
import kotlin.Unit

/**
 * Update the board's refined velocity setting
 * Sets the value of the specified board's refined velocity setting.
 */
public suspend fun HttpClient.setRefinedVelocity(
  input: BooleanSettingBean,
  boardId: Long,
  builder: suspend HttpRequestBuilder.() -> Unit = {},
) {
  val response = put(urlString = """agile/1.0/board/${boardId}/settings/refined-velocity""") {
    contentType(Json)
    setBody(body = input)
    builder()
  }
}
