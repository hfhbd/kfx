package com.sap.hci.api.client

import com.sap.hci.api.GetIntegrationDesigntimeArtifactsByIdAndVersionResourcesByNameAndType
import com.sap.hci.api.GetIntegrationDesigntimeArtifactsByIdAndVersionResourcesByNameAndTypeReferencedResourceType
import com.sap.hci.api.GetIntegrationDesigntimeArtifactsByIdAndVersionResourcesByNameAndTypeSelect
import com.sap.hci.api.GetIntegrationDesigntimeArtifactsByIdAndVersionResourcesByNameAndTypeType
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
 * You can use the following request to get a resource of an integration flow.<br>For further details, refer to the SAP Help Portal documentation [OData API: Integration Content](https://help.sap.com/viewer/368c481cd6954bdfa5d0435479fd4eaf/Cloud/en-US/d1679a80543f46509a7329243b595bdb.html) and to the following SAP Community blog [Remote OData APIs for Integration Flows](https://blogs.sap.com/2018/07/06/cloud-integration-remote-odata-apis-for-integration-flows/).
 *
 * @param id Id of integration artifact <br>
 * Example: ```IntegrationFlowWithConfiguration```
 * @param version Version of integration artifact  <br>
 * You can enter either ```active``` or the version name (e.g. ```1.0.5```) for retrieving the current version.
 * @param name Resource name <br>
 * Example: ```AddSignatureInfo.xsl```
 * @param type Resource type <br>
 * Example: ```xslt```
 * @param referencedResourceType Reference to another resource type (only for resource type 'xsd' the reference to 'wsdl' is allowed).
 * @param select Select properties to be returned.
 */
public suspend fun HttpClient.getIntegrationDesigntimeArtifactsByIdAndVersionResourcesByNameAndType(
  id: String,
  version: String = "active",
  name: String,
  type: GetIntegrationDesigntimeArtifactsByIdAndVersionResourcesByNameAndTypeType,
  referencedResourceType: GetIntegrationDesigntimeArtifactsByIdAndVersionResourcesByNameAndTypeReferencedResourceType? = null,
  select: GetIntegrationDesigntimeArtifactsByIdAndVersionResourcesByNameAndTypeSelect? = null,
  builder: suspend HttpRequestBuilder.() -> Unit = {},
): GetIntegrationDesigntimeArtifactsByIdAndVersionResourcesByNameAndType {
  val response = `get`(urlString = """IntegrationDesigntimeArtifacts(Id='${id}',Version='${version}')/Resources(Name='${name}',ResourceType='${type}')""") {
    parameter("referencedResourceType", referencedResourceType)
    parameter("select", select)
    contentType(Json)
    builder()
  }
  val output = response.body<GetIntegrationDesigntimeArtifactsByIdAndVersionResourcesByNameAndType>()
  return output
}
