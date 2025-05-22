package com.sap.hci.api.client

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.`header`
import io.ktor.client.request.delete
import io.ktor.http.ContentType.Application.Json
import io.ktor.http.contentType
import io.ktor.http.isSuccess
import kotlin.String
import kotlin.Throws
import kotlin.Unit
import odata.Error

/**
 * You can use the following request to delete an integration adapter. <br>
 *
 * This API is available only in Cloud Foundry environment. In API sandbox, only read APIs can be tested. You need to configure an API endpoint for your account, where you have the required write permissions to delete integration adapter.
 *
 * @param id Id of integration adapter artifact <br>
 * Example: ```IntegrationAdapterId```
 * @param X_CSRF_Token CSRF token that is valid for the current session (see resource 'CSRF Token Handling')
 */
@Throws(Error::class)
public suspend fun HttpClient.deleteIntegrationAdapterDesigntimeArtifactsById(
  id: String,
  X_CSRF_Token: String,
  builder: suspend HttpRequestBuilder.() -> Unit = {},
) {
  val response = delete(urlString = """IntegrationAdapterDesigntimeArtifacts(Id='${id}')""") {
    `header`("X-CSRF-Token", X_CSRF_Token)
    contentType(Json)
    builder()
  }
  if (response.status.isSuccess()) {
  } else {
    val output = response.body<Error>()
    throw output
  }
}
