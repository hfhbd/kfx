package com.sap.hci.api.client

import com.sap.hci.api.CreateScriptCollectionDesigntimeArtifacts
import com.sap.hci.api.ScriptCollectionDesigntimeArtifactCreate
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
 * You can use the following request to create or upload a script collection.<br>For further details, refer to the SAP Help Portal documentation [OData API: Integration Content](https://help.sap.com/viewer/368c481cd6954bdfa5d0435479fd4eaf/Cloud/en-US/d1679a80543f46509a7329243b595bdb.html) and to the following SAP Community blog [Remote OData APIs for Integration Flows](https://blogs.sap.com/2018/07/06/cloud-integration-remote-odata-apis-for-integration-flows/).<br>
 *
 * In API sandbox, only read APIs can be tested. You need to configure an API endpoint for your account, where you have the required write permissions to create script collections.
 * @param X_CSRF_Token CSRF token that is valid for the current session (see resource 'CSRF Token Handling')
 */
@Throws(Error::class)
public suspend fun HttpClient.createScriptCollectionDesigntimeArtifacts(
  input: ScriptCollectionDesigntimeArtifactCreate,
  X_CSRF_Token: String,
  builder: suspend HttpRequestBuilder.() -> Unit = {},
): CreateScriptCollectionDesigntimeArtifacts? {
  val response = post(urlString = """ScriptCollectionDesigntimeArtifacts""") {
    `header`("X-CSRF-Token", X_CSRF_Token)
    contentType(Json)
    setBody(body = input)
    builder()
  }
  if (response.status.value == 404) {
    return null
  }
  if (response.status.isSuccess()) {
    return response.body<CreateScriptCollectionDesigntimeArtifacts>()
  } else {
    throw response.body<Error>()
  }
}
