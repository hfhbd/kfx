package com.jira.client

import com.jira.IssueTypeSchemeBean
import com.jira.IssueTypeSchemeCreateUpdateBean
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType.Application.Json
import io.ktor.http.contentType
import kotlin.Unit

/**
 * Create an issue type scheme from JSON representation
 * Creates an issue type scheme from a JSON representation
 */
public suspend fun HttpClient.createIssueTypeScheme(input: IssueTypeSchemeCreateUpdateBean, builder: suspend HttpRequestBuilder.() -> Unit = {}): IssueTypeSchemeBean {
  val response = post(urlString = """api/2/issuetypescheme""") {
    contentType(Json)
    setBody(body = input)
    builder()
  }
  val output = response.body<IssueTypeSchemeBean>()
  return output
}
