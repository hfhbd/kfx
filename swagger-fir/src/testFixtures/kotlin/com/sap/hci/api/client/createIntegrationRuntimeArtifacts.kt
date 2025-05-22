package com.sap.hci.api.client

import com.sap.hci.api.integrationruntimeartifact.Placeholder
import io.ktor.client.HttpClient
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.`header`
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType.Application.Json
import io.ktor.http.contentType
import kotlin.String
import kotlin.Unit

/**
 * __This method is NOT SUPPORTED in CF__. Please use DeployIntegrationDesigntimeArtifact API in the Integration Flow section!
 * @param X_CSRF_Token CSRF token that is valid for the current session (see resource 'CSRF Token Handling')
 */
public suspend fun HttpClient.createIntegrationRuntimeArtifacts(
  input: Placeholder,
  X_CSRF_Token: String,
  builder: suspend HttpRequestBuilder.() -> Unit = {},
) {
  val response = post(urlString = """IntegrationRuntimeArtifacts""") {
    `header`("X-CSRF-Token", X_CSRF_Token)
    contentType(Json)
    setBody(body = input)
    builder()
  }
}
