package com.sap.hci.api.client

import com.sap.hci.api.GetIntegrationRuntimeArtifacts
import com.sap.hci.api.GetIntegrationRuntimeArtifactsInlinecount
import com.sap.hci.api.GetIntegrationRuntimeArtifactsOrderby
import com.sap.hci.api.GetIntegrationRuntimeArtifactsSelect
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.`get`
import io.ktor.client.request.parameter
import kotlin.Int
import kotlin.String
import kotlin.Unit

/**
 * You can use the following request to get all deployed integration artifacts.<br>
 * In API sandbox the following integration flows are deployed:<br>
 * * '__Integration Flow - FAILED DEPLOYMENT__' with deployment error information
 * * '__Integration Flow with MessageStore - COMPLETED PROCESSING__' with attachments and message store entries
 * * '__Integration Flow with Adapter Data - FAILED PROCESSING__' with message processing log error information, attachments, custom header properties and adapater data
 *
 * @param select Select properties to be returned.
 * @param filter Filter items by property values (not possible for DeployedOn). No nesting possible. Only logical operator ```or``` is allowed. <br>
 * Example1: ```Status eq 'ERROR'``` returns all deployed integration artifacts in status 'ERROR'.<br>
 * Example2: ```Status eq 'ERROR' or Version eq '1.0.0'```
 * @param top Show only the first n items.
 * @param skip Skip the first n items.
 * @param inlinecount Count the number of retrieved entries by selecting ```allpages```.
 * @param orderby Order items by property values, see [Orderby System Query Option](https://www.odata.org/documentation/odata-version-2-0/uri-conventions/)
 */
public suspend fun HttpClient.getIntegrationRuntimeArtifacts(
  select: GetIntegrationRuntimeArtifactsSelect? = null,
  filter: String? = null,
  top: Int? = null,
  skip: Int? = null,
  inlinecount: GetIntegrationRuntimeArtifactsInlinecount? = null,
  orderby: GetIntegrationRuntimeArtifactsOrderby? = null,
  builder: suspend HttpRequestBuilder.() -> Unit = {},
): GetIntegrationRuntimeArtifacts {
  val response = `get`(urlString = """IntegrationRuntimeArtifacts""") {
    parameter("select", select)
    parameter("filter", filter)
    parameter("top", top)
    parameter("skip", skip)
    parameter("inlinecount", inlinecount)
    parameter("orderby", orderby)
    builder()
  }
  val output = response.body<GetIntegrationRuntimeArtifacts>()
  return output
}
