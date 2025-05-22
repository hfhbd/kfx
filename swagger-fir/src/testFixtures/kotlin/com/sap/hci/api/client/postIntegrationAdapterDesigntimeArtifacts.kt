package com.sap.hci.api.client

import com.sap.hci.api.IntegrationAdapterDesigntimeArtifactImport
import com.sap.hci.api.PostIntegrationAdapterDesigntimeArtifacts
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.`header`
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType.Application.Json
import io.ktor.http.contentType
import io.ktor.http.isSuccess
import kotlin.String
import kotlin.Throws
import kotlin.Unit
import odata.Error

/**
 * You can use the following request to import an integration adapter artifact.<br>
 *
 * This API is available only in the Cloud Foundry environment. <br>For more information, see SAP Help Portal documentation at [Environment-Specific Aspects Integration Developers Should Know](https://help.sap.com/docs/CLOUD_INTEGRATION/368c481cd6954bdfa5d0435479fd4eaf/639a0612e32c498fa7480580d90c9ea6.html?locale=en-US).<br>In API sandbox, only read APIs can be tested. You need to configure an API endpoint for your account, where you have the required write permissions to import adapters.
 * @param X_CSRF_Token CSRF token that is valid for the current session (see resource 'CSRF Token Handling')
 */
@Throws(Error::class)
public suspend fun HttpClient.postIntegrationAdapterDesigntimeArtifacts(
  input: IntegrationAdapterDesigntimeArtifactImport,
  X_CSRF_Token: String,
  builder: suspend HttpRequestBuilder.() -> Unit = {},
): PostIntegrationAdapterDesigntimeArtifacts? {
  val response = post(urlString = """IntegrationAdapterDesigntimeArtifacts""") {
    `header`("X-CSRF-Token", X_CSRF_Token)
    contentType(Json)
    setBody(body = input)
    builder()
  }
  if (response.status.value == 404) {
    return null
  }
  if (response.status.isSuccess()) {
    val output = response.body<PostIntegrationAdapterDesigntimeArtifacts>()
    return output
  } else {
    val output = response.body<Error>()
    throw output
  }
}
