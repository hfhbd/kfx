package com.sap.hci.api.client

import io.ktor.client.HttpClient
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.`header`
import io.ktor.client.request.delete
import io.ktor.http.ContentType.Application.Json
import io.ktor.http.contentType
import kotlin.String
import kotlin.Unit

/**
 * You can use the following request to undeploy an integration artifact.<br>
 *
 * In API sandbox, only read APIs can be tested. You need to configure an API endpoint for your account, where you have the required write permissions to undeploy an integration artifact.
 *
 * @param id Id of deployed integration artifact <br>
 * Example: ```IntegrationFlow_MessageStore_COMPLETED_PROCESSING```
 * @param X_CSRF_Token CSRF token that is valid for the current session (see resource 'CSRF Token Handling')
 */
public suspend fun HttpClient.deleteIntegrationRuntimeArtifactsById(
  id: String,
  X_CSRF_Token: String,
  builder: suspend HttpRequestBuilder.() -> Unit = {},
) {
  val response = delete(urlString = """IntegrationRuntimeArtifacts('${id}')""") {
    `header`("X-CSRF-Token", X_CSRF_Token)
    contentType(Json)
    builder()
  }
}
