package com.jira.client

import com.jira.UserAnonymizationRequestBean
import io.ktor.client.HttpClient
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType.Application.Json
import io.ktor.http.contentType
import kotlin.Unit

/**
 * Schedule user anonymization
 * Schedules a user anonymization process. Requires system admin permission.
 */
public suspend fun HttpClient.scheduleUserAnonymization(input: UserAnonymizationRequestBean, builder: suspend HttpRequestBuilder.() -> Unit = {}) {
  val response = post(urlString = """api/2/user/anonymization""") {
    contentType(Json)
    setBody(body = input)
    builder()
  }
}
