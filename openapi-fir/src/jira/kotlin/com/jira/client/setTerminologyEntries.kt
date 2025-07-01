package com.jira.client

import com.jira.TerminologyRequestBean
import io.ktor.client.HttpClient
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType.Application.Json
import io.ktor.http.contentType
import kotlin.Unit

/**
 * Update epic/sprint names from original to new
 * Change epic/sprint names from {originalName} to {newName}. The {newName} will be displayed in Jira instead of {originalName}
 * {"originalName"} must be equal to "epic" or "sprint".
 * There can be only one entry per unique {"originalName"}.
 * {"newName"} can only consist of alphanumeric characters and spaces e.g. {"newName": "iteration number 2"}.
 * {"newName"} must be between 1 to 100 characters.
 * It can't use the already defined {"newName"} values or restricted JQL words.
 * To reset {"newName"} to the default value, enter the {"originalName"} value as the value for {"newName"}. For example, if you want to return to {"originalName": "sprint"}, enter {"newName": "sprint"}.
 */
public suspend fun HttpClient.setTerminologyEntries(input: TerminologyRequestBean, builder: suspend HttpRequestBuilder.() -> Unit = {}) {
  val response = post(urlString = """api/2/terminology/entries""") {
    contentType(Json)
    setBody(body = input)
    builder()
  }
}
