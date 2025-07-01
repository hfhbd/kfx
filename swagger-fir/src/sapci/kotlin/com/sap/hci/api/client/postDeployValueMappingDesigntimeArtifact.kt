package com.sap.hci.api.client

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.`header`
import io.ktor.client.request.parameter
import io.ktor.client.request.post
import io.ktor.http.ContentType.Application.Json
import io.ktor.http.contentType
import io.ktor.http.isSuccess
import kotlin.String
import kotlin.Throws
import kotlin.Unit
import odata.Error

/**
 * You can use the following request to deploy a value mapping.<br>
 *
 * In API sandbox, only read APIs can be tested. You need to configure an API endpoint for your account, where you have the required write permissions to deploy value mappings.
 *
 * @param X_CSRF_Token CSRF token that is valid for the current session (see resource 'CSRF Token Handling')
 * @param id Id of value mapping artifact - enclosed in single quotes <br>
 * Example: ```'ValueMapping1'```
 * @param version Version of value mapping artifact - enclosed in single quotes  <br>
 * You can enter either ```'active'``` or the version name (e.g. ```'1.0.5'```) for retrieving the current version.
 */
@Throws(Error::class)
public suspend fun HttpClient.postDeployValueMappingDesigntimeArtifact(
  X_CSRF_Token: String,
  id: String? = null,
  version: String? = "'active'",
  builder: suspend HttpRequestBuilder.() -> Unit = {},
) {
  val response = post(urlString = """DeployValueMappingDesigntimeArtifact""") {
    `header`("X-CSRF-Token", X_CSRF_Token)
    parameter("id", id)
    parameter("version", version)
    contentType(Json)
    builder()
  }
  if (response.status.isSuccess()) {
  } else {
    val output = response.body<Error>()
    throw output
  }
}
