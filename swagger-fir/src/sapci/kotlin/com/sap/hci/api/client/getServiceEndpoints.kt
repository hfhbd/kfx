package com.sap.hci.api.client

import com.sap.hci.api.GetServiceEndpoints
import com.sap.hci.api.GetServiceEndpointsExpand
import com.sap.hci.api.GetServiceEndpointsFormat
import com.sap.hci.api.GetServiceEndpointsInlinecount
import com.sap.hci.api.GetServiceEndpointsSelect
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.`get`
import io.ktor.client.request.parameter
import kotlin.Int
import kotlin.String
import kotlin.Unit

/**
 * You can use the following request to get all endpoints provided for deployed integration flows.<br>For further details, refer to the SAP Help Portal documentation [OData API: Integration Content](https://help.sap.com/viewer/368c481cd6954bdfa5d0435479fd4eaf/Cloud/en-US/d1679a80543f46509a7329243b595bdb.html) and to the following SAP Community blog [SAP Cloud Integration - OData API for accessing the service endpoints](https://blogs.sap.com/2019/03/25/sap-cloud-platform-integration-odata-api-for-accessing-the-service-endpoints/).
 *
 * @param top Show only the first n items.
 * @param skip Skip the first n items.
 * @param filter Returns a subset of the entries, which matches the filter condition.<br>
 * Example: ```Name eq 'IntegrationFlowWithConfiguration'``` provides all endpoints of the deployed integration flow with the ID IntegrationFlowWithConfiguration.<br>
 * Example: ```Protocol eq 'SOAP'``` provides all endpoints of the deployed integration flows with SOAP protocol type.
 * @param select Select properties to be returned.
 * @param expand Expand related entities.
 * @param inlinecount Count the number of retrieved entries by selecting ```allpages```.
 * @param format Response format can be JSON or XML.
 */
public suspend fun HttpClient.getServiceEndpoints(
  top: Int? = null,
  skip: Int? = null,
  filter: String? = null,
  select: GetServiceEndpointsSelect? = null,
  expand: GetServiceEndpointsExpand? = null,
  inlinecount: GetServiceEndpointsInlinecount? = null,
  format: GetServiceEndpointsFormat? = null,
  builder: suspend HttpRequestBuilder.() -> Unit = {},
): GetServiceEndpoints {
  val response = `get`(urlString = """ServiceEndpoints""") {
    parameter("top", top)
    parameter("skip", skip)
    parameter("filter", filter)
    parameter("select", select)
    parameter("expand", expand)
    parameter("inlinecount", inlinecount)
    parameter("format", format)
    builder()
  }
  val output = response.body<GetServiceEndpoints>()
  return output
}
