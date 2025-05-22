package com.jira.client

import com.jira.AddGroupBean
import com.jira.GroupBean
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType.Application.Json
import io.ktor.http.contentType
import kotlin.Unit

/**
 * Create a group with given parameters
 * Creates a group by given group parameter
 */
public suspend fun HttpClient.createGroup(input: AddGroupBean, builder: suspend HttpRequestBuilder.() -> Unit = {}): GroupBean {
  val response = post(urlString = """api/2/group""") {
    contentType(Json)
    setBody(body = input)
    builder()
  }
  val output = response.body<GroupBean>()
  return output
}
