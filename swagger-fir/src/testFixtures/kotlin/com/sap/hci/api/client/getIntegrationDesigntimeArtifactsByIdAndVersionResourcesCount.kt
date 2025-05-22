package com.sap.hci.api.client

import io.ktor.client.HttpClient
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.`get`
import io.ktor.client.request.parameter
import kotlin.String
import kotlin.Unit

/**
 * You can use the following request to get the number of all resources for an integration flow.<br>For further details, refer to the SAP Help Portal documentation [OData API: Integration Content](https://help.sap.com/viewer/368c481cd6954bdfa5d0435479fd4eaf/Cloud/en-US/d1679a80543f46509a7329243b595bdb.html) and to the following SAP Community blog [Remote OData APIs for Integration Flows](https://blogs.sap.com/2018/07/06/cloud-integration-remote-odata-apis-for-integration-flows/).
 *
 * @param id Id of integration artifact <br>
 * Example: ```IntegrationFlowWithConfiguration```
 * @param version Version of integration artifact  <br>
 * You can enter either ```active``` or the version name (e.g. ```1.0.5```) for retrieving the current version.
 * @param filter Returns a subset of the entries, which matches the filter condition.<br>
 * Supported operators: ```eq``` and ```ne```<br>
 * Supported string functions: ```substringof```, ```startswith``` and ```endswith```.<br>
 * Example: ```substringof('wsdl',Name) eq true```
 */
public suspend fun HttpClient.getIntegrationDesigntimeArtifactsByIdAndVersionResourcesCount(
  id: String,
  version: String = "active",
  filter: String? = null,
  builder: suspend HttpRequestBuilder.() -> Unit = {},
) {
  val response = `get`(urlString = """IntegrationDesigntimeArtifacts(Id='${id}',Version='${version}')/Resources/${'$'}count""") {
    parameter("filter", filter)
    builder()
  }
}
