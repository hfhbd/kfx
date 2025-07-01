package com.sap.hci.api.client

import io.ktor.client.HttpClient
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.`header`
import io.ktor.client.request.parameter
import io.ktor.client.request.post
import io.ktor.http.ContentType.Application.Json
import io.ktor.http.contentType
import kotlin.String
import kotlin.Unit

/**
 * You can use the following request to update an artifact with new specified version.<br>
 *
 * In API sandbox, only read APIs can be tested. You need to configure an API endpoint for your account, where you have the required write permissions to update value mappings.
 *
 * @param X_CSRF_Token CSRF token that is valid for the current session (see resource 'CSRF Token Handling')
 * @param id Id of value mapping artifact <br>
 * Example: ```ValueMapping1```
 * @param saveAsVersion New version of value mapping artifact <br>
 *  (e.g. ```1.0.5```).
 */
public suspend fun HttpClient.postValueMappingDesigntimeArtifactSaveAsVersion(
  X_CSRF_Token: String,
  id: String? = null,
  saveAsVersion: String? = null,
  builder: suspend HttpRequestBuilder.() -> Unit = {},
) {
  val response = post(urlString = """ValueMappingDesigntimeArtifactSaveAsVersion""") {
    `header`("X-CSRF-Token", X_CSRF_Token)
    parameter("id", id)
    parameter("saveAsVersion", saveAsVersion)
    contentType(Json)
    builder()
  }
}
