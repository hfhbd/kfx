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
 * You can use the following request to deploy a message mapping.<br>For further details, refer to the SAP Help Portal documentation [OData API: Integration Content](https://help.sap.com/viewer/368c481cd6954bdfa5d0435479fd4eaf/Cloud/en-US/d1679a80543f46509a7329243b595bdb.html) and to the following SAP Community blog [Remote OData APIs for Integration Flows](https://blogs.sap.com/2018/07/06/cloud-integration-remote-odata-apis-for-integration-flows/).<br>
 *
 * In API sandbox, only read APIs can be tested. You need to configure an API endpoint for your account, where you have the required write permissions to deploy message mappings.
 *
 * @param X_CSRF_Token CSRF token that is valid for the current session (see resource 'CSRF Token Handling')
 * @param id Id of message mapping - enclosed in single quotes <br>
 * @param version Version of message mapping - enclosed in single quotes  <br>
 * You can enter either ```'active'``` or the version name (e.g. ```'1.0.5'```) for retrieving the current version.
 */
@Throws(Error::class)
public suspend fun HttpClient.createDeployMessageMappingDesigntimeArtifact(
  X_CSRF_Token: String,
  id: String? = null,
  version: String? = "'active'",
  builder: suspend HttpRequestBuilder.() -> Unit = {},
) {
  val response = post(urlString = """DeployMessageMappingDesigntimeArtifact""") {
    `header`("X-CSRF-Token", X_CSRF_Token)
    parameter("id", id)
    parameter("version", version)
    builder()
  }
  if (response.status.isSuccess()) {
  } else {
    throw response.body<Error>()
  }
}
