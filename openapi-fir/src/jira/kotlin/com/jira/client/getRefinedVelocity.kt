package com.jira.client

import com.jira.BooleanSettingBean
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.`get`
import io.ktor.http.HttpStatusCode.Companion.NotFound
import kotlin.Long
import kotlin.Unit

/**
 * Get the value of the refined velocity setting
 * Returns the value of the setting for refined velocity chart
 */
public suspend fun HttpClient.getRefinedVelocity(boardId: Long, builder: suspend HttpRequestBuilder.() -> Unit = {}): BooleanSettingBean? {
  val response = `get`(urlString = """agile/1.0/board/${boardId}/settings/refined-velocity""") {
    builder()
  }
  when {
    response.status == NotFound -> {
      return null
    }
  }
  val output = response.body<BooleanSettingBean>()
  return output
}
