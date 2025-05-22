package com.sap.hci.api.client

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.`get`
import io.ktor.http.ContentType.Application.Json
import io.ktor.http.contentType
import kotlin.String
import kotlin.Unit
import kotlinx.io.Source

/**
 * You can use the following request to download a message mapping as zip file. Message mappings of configure-only packages cannot be downloaded<br>For further details, refer to the SAP Help Portal documentation [OData API: Integration Content](https://help.sap.com/viewer/368c481cd6954bdfa5d0435479fd4eaf/Cloud/en-US/d1679a80543f46509a7329243b595bdb.html) and to the following SAP Community blog [Remote OData APIs for Integration Flows](https://blogs.sap.com/2018/07/06/cloud-integration-remote-odata-apis-for-integration-flows/).<br>
 *
 * @param id Id of message mapping<br>
 * @param version Version of message mapping <br>
 * You can enter either ```active``` or the version name (e.g. ```1.0.5```) for retrieving the current version.
 */
public suspend fun HttpClient.getMessageMappingDesigntimeArtifactsByIdAndVersionValue(
  id: String,
  version: String = "active",
  builder: suspend HttpRequestBuilder.() -> Unit = {},
): Source? {
  val response = `get`(urlString = """MessageMappingDesigntimeArtifacts(Id='${id}',Version='${version}')/${'$'}value""") {
    contentType(Json)
    builder()
  }
  if (response.status.value == 404) {
    return null
  }
  val output = response.body<Source>()
  return output
}
