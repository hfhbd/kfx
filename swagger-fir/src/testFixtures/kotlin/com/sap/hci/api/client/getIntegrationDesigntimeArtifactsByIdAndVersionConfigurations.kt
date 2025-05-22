package com.sap.hci.api.client

import com.sap.hci.api.GetIntegrationDesigntimeArtifactsByIdAndVersionConfigurations
import com.sap.hci.api.GetIntegrationDesigntimeArtifactsByIdAndVersionConfigurationsFormat
import com.sap.hci.api.GetIntegrationDesigntimeArtifactsByIdAndVersionConfigurationsOrderby
import com.sap.hci.api.GetIntegrationDesigntimeArtifactsByIdAndVersionConfigurationsSelect
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.`get`
import io.ktor.client.request.parameter
import io.ktor.http.ContentType.Application.Json
import io.ktor.http.contentType
import kotlin.String
import kotlin.Unit

/**
 * You can use the following request to get the configuration parameters (key/value pairs) of an integration artifact by Id and version.<br>For further details, refer to the SAP Help Portal documentation [OData API: Integration Content](https://help.sap.com/viewer/368c481cd6954bdfa5d0435479fd4eaf/Cloud/en-US/d1679a80543f46509a7329243b595bdb.html) and to the following SAP Community blog [Remote OData APIs for Integration Flows](https://blogs.sap.com/2018/07/06/cloud-integration-remote-odata-apis-for-integration-flows/).<br>
 * In API sandbox, the following integration flow contains configuration parameters: '__IntegrationFlowWithConfiguration__' with version '__1.0.5__'
 *
 * @param id Id of integration artifact <br>
 * Example: ```IntegrationFlowWithConfiguration```
 * @param version Version of integration artifact  <br>
 * You can enter either ```active``` or the version name (e.g. ```1.0.5```) for retrieving the current version.
 * @param filter Returns a subset of the entries, which matches the filter condition.<br>
 * Supported operators: ```eq``` and ```ne```<br>
 * Supported string functions: ```substringof```, ```startswith``` and ```endswith```.<br>
 * Example: ```substringof('Receiver_',ParameterKey) eq true```
 * @param select Select properties to be returned.
 * @param orderby Order items by property values.
 * @param format Response format can be JSON or XML.
 */
public suspend fun HttpClient.getIntegrationDesigntimeArtifactsByIdAndVersionConfigurations(
  id: String,
  version: String = "active",
  filter: String? = null,
  select: GetIntegrationDesigntimeArtifactsByIdAndVersionConfigurationsSelect? = null,
  orderby: GetIntegrationDesigntimeArtifactsByIdAndVersionConfigurationsOrderby? = null,
  format: GetIntegrationDesigntimeArtifactsByIdAndVersionConfigurationsFormat? = null,
  builder: suspend HttpRequestBuilder.() -> Unit = {},
): GetIntegrationDesigntimeArtifactsByIdAndVersionConfigurations {
  val response = `get`(urlString = """IntegrationDesigntimeArtifacts(Id='${id}',Version='${version}')/Configurations""") {
    parameter("filter", filter)
    parameter("select", select)
    parameter("orderby", orderby)
    parameter("format", format)
    contentType(Json)
    builder()
  }
  val output = response.body<GetIntegrationDesigntimeArtifactsByIdAndVersionConfigurations>()
  return output
}
