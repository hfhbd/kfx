package com.sap.hci.api.client

import com.sap.hci.api.CreateScriptCollectionDesigntimeArtifactsByIdAndVersionResources
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.`header`
import io.ktor.client.request.post
import io.ktor.http.isSuccess
import kotlin.String
import kotlin.Throws
import kotlin.Unit
import odata.Error

/**
 * You can use the following request to add a resource to a script collection.<br>For further details, refer to the SAP Help Portal documentation [OData API: Integration Content](https://help.sap.com/viewer/368c481cd6954bdfa5d0435479fd4eaf/Cloud/en-US/d1679a80543f46509a7329243b595bdb.html) .<br>
 *
 * In API sandbox, only read APIs can be tested. You need to configure an API endpoint for your account, where you have the required write permissions to update script collections.
 *
 * @param id Id of a script collection artifact <br>
 * Example: **ScriptCollection1**
 * @param version Version of a script collection artifact  <br>
 * You can enter either **active** or the version name (e.g. **1.0.2**) for the current version.
 * @param X_CSRF_Token CSRF token that is valid for the current session (see resource 'CSRF Token Handling')
 */
@Throws(Error::class)
public suspend fun HttpClient.createScriptCollectionDesigntimeArtifactsByIdAndVersionResources(
  id: String,
  version: String = "active",
  X_CSRF_Token: String,
  builder: suspend HttpRequestBuilder.() -> Unit = {},
): CreateScriptCollectionDesigntimeArtifactsByIdAndVersionResources? {
  val response = post(urlString = """ScriptCollectionDesigntimeArtifacts(Id='${id}',Version='${version}')/Resources""") {
    `header`("X-CSRF-Token", X_CSRF_Token)
    builder()
  }
  if (response.status.value == 404) {
    return null
  }
  if (response.status.isSuccess()) {
    return response.body<CreateScriptCollectionDesigntimeArtifactsByIdAndVersionResources>()
  } else {
    throw response.body<Error>()
  }
}
