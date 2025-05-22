package com.sap.hci.api.client

import com.sap.hci.api.CreateValueMappingDesigntimeArtifacts
import com.sap.hci.api.ValueMappingDesigntimeArtifactCreate
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
 * You can use the following request to upload a value mapping. 
 * In API sandbox, only read APIs can be tested. You need to configure an API endpoint for your account, where you have the required write permissions to upload value mappings.
 * @param X_CSRF_Token CSRF token that is valid for the current session (see resource 'CSRF Token Handling')
 */
@Throws(Error::class)
public suspend fun HttpClient.createValueMappingDesigntimeArtifacts(
  input: ValueMappingDesigntimeArtifactCreate,
  X_CSRF_Token: String,
  builder: suspend HttpRequestBuilder.() -> Unit = {},
): CreateValueMappingDesigntimeArtifacts? {
  val response = post(urlString = """ValueMappingDesigntimeArtifacts""") {
    `header`("X-CSRF-Token", X_CSRF_Token)
    contentType(Json)
    setBody(body = input)
    builder()
  }
  if (response.status.value == 404) {
    return null
  }
  if (response.status.isSuccess()) {
    return response.body<CreateValueMappingDesigntimeArtifacts>()
  } else {
    throw response.body<Error>()
  }
}
