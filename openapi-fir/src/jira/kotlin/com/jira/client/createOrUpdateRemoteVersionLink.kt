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
 * Create or update remote version link without global ID
 * Create a remote version link via POST. The link's global ID will be taken from the JSON payload if provided; otherwise, it will be generated.
 */
public suspend fun HttpClient.createOrUpdateRemoteVersionLink(
  input: RemoteEntityLinkJsonBean,
  versionId: String,
  builder: suspend HttpRequestBuilder.() -> Unit = {},
) {
  val response = post(urlString = """api/2/version/${versionId}/remotelink""") {
    contentType(Json)
    setBody(body = input)
    builder()
  }
}
