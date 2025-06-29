package com.sap.hci.api.client

import io.ktor.client.HttpClient
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.`get`
import io.ktor.client.request.parameter
import kotlin.String
import kotlin.Unit

/**
 * You can use the following request to get the number of all endpoints provided for deployed integration flows.<br>For further details, refer to the SAP Help Portal documentation [OData API: Integration Content](https://help.sap.com/viewer/368c481cd6954bdfa5d0435479fd4eaf/Cloud/en-US/d1679a80543f46509a7329243b595bdb.html) and to the following SAP Community blog [SAP Cloud Integration - OData API for accessing the service endpoints](https://blogs.sap.com/2019/03/25/sap-cloud-platform-integration-odata-api-for-accessing-the-service-endpoints/).
 *
 * @param filter Returns a subset of the entries, which matches the filter condition.<br>
 * Example: ```Name eq 'IntegrationFlowWithConfiguration'``` provides all endpoints of the deployed integration flow with the ID IntegrationFlowWithConfiguration.<br>
 * Example: ```Protocol eq 'SOAP'``` provides all endpoints of the deployed integration flows with SOAP protocol type.
 */
public suspend fun HttpClient.getServiceEndpointsCount(filter: String? = null, builder: suspend HttpRequestBuilder.() -> Unit = {}) {
  val response = `get`(urlString = """ServiceEndpoints/${'$'}count""") {
    parameter("filter", filter)
    builder()
  }
}
