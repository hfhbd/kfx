package com.sap.hci.api.client

import com.sap.hci.api.IntegrationDesigntimeArtifactUpdate
import io.ktor.client.HttpClient
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.`header`
import io.ktor.client.request.put
import io.ktor.client.request.setBody
import io.ktor.http.ContentType.Application.Json
import io.ktor.http.contentType
import kotlin.String
import kotlin.Unit

/**
 * You can use the following request to update an integration flow.<br>
 *
 * In API sandbox, only read APIs can be tested. You need to configure an API endpoint for your account, where you have the required write permissions to update integration flows.
 * @param id Id of integration artifact <br>
 * Example: ```IntegrationFlowWithConfiguration```
 * @param version Current version of integration artifact to be updated <br>
 * You can enter either ```active``` or the current/latest version name (e.g. ```1.0.5```).
 * @param X_CSRF_Token CSRF token that is valid for the current session (see resource 'CSRF Token Handling')
 */
public suspend fun HttpClient.putIntegrationDesigntimeArtifactsByIdAndVersion(
  input: IntegrationDesigntimeArtifactUpdate,
  id: String,
  version: String = "active",
  X_CSRF_Token: String,
  builder: suspend HttpRequestBuilder.() -> Unit = {},
) {
  val response = put(urlString = """IntegrationDesigntimeArtifacts(Id='${id}',Version='${version}')""") {
    `header`("X-CSRF-Token", X_CSRF_Token)
    contentType(Json)
    setBody(body = input)
    builder()
  }
}
