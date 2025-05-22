package com.jira.client

import com.jira.UserAnonymizationRerunRequestBean
import io.ktor.client.HttpClient
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType.Application.Json
import io.ktor.http.contentType
import kotlin.Unit

/**
 * Schedule user anonymization rerun
 * Schedules a user anonymization process. Requires system admin permission.
 */
public suspend fun HttpClient.scheduleUserAnonymizationRerun(input: UserAnonymizationRerunRequestBean, builder: suspend HttpRequestBuilder.() -> Unit = {}) {
  val response = post(urlString = """api/2/user/anonymization/rerun""") {
    contentType(Json)
    setBody(body = input)
    builder()
  }
}
