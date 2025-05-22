package com.sap.hci.api.client

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.`header`
import io.ktor.client.request.parameter
import io.ktor.client.request.post
import io.ktor.http.isSuccess
import kotlin.String
import kotlin.Throws
import kotlin.Unit
import odata.Error

/**
 * You can use the following request to deploy an integration adapter.<br>
 *
 * This API is available only in Cloud Foundry environment. In API sandbox, only read APIs can be tested. You need to configure an API endpoint for your account, where you have the required write permissions to deploy integration adapter.
 *
 * @param X_CSRF_Token CSRF token that is valid for the current session (see resource 'CSRF Token Handling')
 * @param id Id of integration adapter artifact - enclosed in single quotes <br>
 * Example: ```'IntegrationAdapterId'```
 */
@Throws(Error::class)
public suspend fun HttpClient.createDeployIntegrationAdapterDesigntimeArtifact(
  X_CSRF_Token: String,
  id: String? = null,
  builder: suspend HttpRequestBuilder.() -> Unit = {},
) {
  val response = post(urlString = """DeployIntegrationAdapterDesigntimeArtifact""") {
    `header`("X-CSRF-Token", X_CSRF_Token)
    parameter("id", id)
    builder()
  }
  if (response.status.isSuccess()) {
  } else {
    throw response.body<Error>()
  }
}
