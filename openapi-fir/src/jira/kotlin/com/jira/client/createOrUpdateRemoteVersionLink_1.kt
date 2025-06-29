package com.jira.client

import com.jira.RemoteEntityLinkJsonBean
import io.ktor.client.HttpClient
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType.Application.Json
import io.ktor.http.contentType
import kotlin.String
import kotlin.Unit

/**
 * Create or update remote version link with global ID
 * Create a remote version link via POST using the provided global ID.
 */
public suspend fun HttpClient.createOrUpdateRemoteVersionLink_1(
  input: RemoteEntityLinkJsonBean,
  versionId: String,
  globalId: String,
  builder: suspend HttpRequestBuilder.() -> Unit = {},
) {
  val response = post(urlString = """api/2/version/${versionId}/remotelink/${globalId}""") {
    contentType(Json)
    setBody(body = input)
    builder()
  }
}
