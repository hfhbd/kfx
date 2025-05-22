package com.jira.client

import io.ktor.client.HttpClient
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.put
import io.ktor.http.ContentType.Application.FormUrlEncoded
import io.ktor.http.contentType
import kotlin.Unit

/**
 * Set default system columns for issue navigator using form
 * Sets the default system columns for issue navigator. Admin permission will be required.
 */
public suspend fun HttpClient.setIssueNavigatorDefaultColumnsForm(builder: suspend HttpRequestBuilder.() -> Unit = {}) {
  val response = put(urlString = """api/2/settings/columns""") {
    contentType(FormUrlEncoded)
    builder()
  }
}
