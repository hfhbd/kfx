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
 * You can use the following request to delete a message mapping.<br>For further details, refer to the SAP Help Portal documentation [OData API: Integration Content](https://help.sap.com/viewer/368c481cd6954bdfa5d0435479fd4eaf/Cloud/en-US/d1679a80543f46509a7329243b595bdb.html) and to the following SAP Community blog [Remote OData APIs for Integration Flows](https://blogs.sap.com/2018/07/06/cloud-integration-remote-odata-apis-for-integration-flows/).<br>
 *
 * In API sandbox, only read APIs can be tested. You need to configure an API endpoint for your account, where you have the required write permissions to delete integration flows.
 *
 * @param id Id of message mapping <br>
 * Example: ```MessageMapping1```
 * @param version Version of message mapping artifact.<br>
 * You can enter either ```active``` or the version name (e.g. ```1.0.5```) for retrieving the current version.
 * @param X_CSRF_Token CSRF token that is valid for the current session (see resource 'CSRF Token Handling')
 */
public suspend fun HttpClient.deleteMessageMappingDesigntimeArtifactsByIdAndVersion(
  id: String,
  version: String = "active",
  X_CSRF_Token: String,
  builder: suspend HttpRequestBuilder.() -> Unit = {},
) {
  val response = delete(urlString = """MessageMappingDesigntimeArtifacts(Id='${id}',Version='${version}')""") {
    `header`("X-CSRF-Token", X_CSRF_Token)
    contentType(Json)
    builder()
  }
}
