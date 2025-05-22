package com.sap.hci.api.client

import com.sap.hci.api.ScriptCollectionDesigntimeArtifactUpdate
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.`header`
import io.ktor.client.request.put
import io.ktor.client.request.setBody
import io.ktor.http.ContentType.Application.Json
import io.ktor.http.contentType
import io.ktor.http.isSuccess
import kotlin.String
import kotlin.Throws
import kotlin.Unit
import odata.Error

/**
 * You can use the following request to update a script collection artifact.<br>
 *
 * In API sandbox, only read APIs can be tested. You need to configure an API endpoint for your account, where you have the required write permissions to update integration flows.
 * @param id Id of script collection artifact<br>
 * Example: **Script collection1**
 * @param version Current version of script collection artifact to be updated <br>
 * You can enter either **active** or the current/latest version name (e.g. **1.0.5**).
 * @param X_CSRF_Token CSRF token that is valid for the current session (see resource 'CSRF Token Handling')
 */
@Throws(Error::class)
public suspend fun HttpClient.upgradeScriptCollectionDesigntimeArtifactsByIdAndVersion(
  input: ScriptCollectionDesigntimeArtifactUpdate,
  id: String,
  version: String = "active",
  X_CSRF_Token: String,
  builder: suspend HttpRequestBuilder.() -> Unit = {},
) {
  val response = put(urlString = """ScriptCollectionDesigntimeArtifacts(Id='${id}',Version='${version}')""") {
    `header`("X-CSRF-Token", X_CSRF_Token)
    contentType(Json)
    setBody(body = input)
    builder()
  }
  if (response.status.isSuccess()) {
  } else {
    throw response.body<Error>()
  }
}
