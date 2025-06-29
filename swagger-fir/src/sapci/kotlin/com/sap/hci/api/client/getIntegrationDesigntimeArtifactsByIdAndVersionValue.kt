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
 * You can use the following request to download an integration flow as zip file. Integration flows of configure-only packages cannot be downloaded.<br>For further details, refer to the SAP Help Portal documentation [OData API: Integration Content](https://help.sap.com/viewer/368c481cd6954bdfa5d0435479fd4eaf/Cloud/en-US/d1679a80543f46509a7329243b595bdb.html) and to the following SAP Community blog [Remote OData APIs for Integration Flows](https://blogs.sap.com/2018/07/06/cloud-integration-remote-odata-apis-for-integration-flows/).<br>
 * In API sandbox the following integration flows with Id and version are available:
 * * '__IntegrationFlow_FAILED_DEPLOYMENT__' with version '__1.0.0__' 
 * * '__IntegrationFlow_MessageStore_COMPLETED_PROCESSING__' with version '__1.0.0__'
 * * '__IntegrationFlow_AdapterData_FAILED_PROCESSING__' with version '__1.0.0__'
 * * '__IntegrationFlowWithConfiguration__' with version '__1.0.5__'
 *
 * @param id Id of integration artifact <br>
 * Example: ```IntegrationFlowWithConfiguration```
 * @param version Version of integration artifact  <br>
 * You can enter either ```active``` or the version name (e.g. ```1.0.5```) for retrieving the current version.
 */
public suspend fun HttpClient.getIntegrationDesigntimeArtifactsByIdAndVersionValue(
  id: String,
  version: String = "active",
  builder: suspend HttpRequestBuilder.() -> Unit = {},
): Source {
  val response = `get`(urlString = """IntegrationDesigntimeArtifacts(Id='${id}',Version='${version}')/${'$'}value""") {
    contentType(Json)
    builder()
  }
  val output = response.body<Source>()
  return output
}
