package com.sap.hci.api.client

import com.sap.hci.api.GetIntegrationRuntimeArtifactsById
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.`get`
import io.ktor.http.ContentType.Application.Json
import io.ktor.http.contentType
import kotlin.String
import kotlin.Unit

/**
 * You can use the following request to get a deployed integration artifact by Id.<br>
 * In API sandbox integration flows with following Ids are deployed:
 * * '__IntegrationFlow_FAILED_DEPLOYMENT__' with deployment error information
 * * '__IntegrationFlow_MessageStore_COMPLETED_PROCESSING__' with attachments and message store entries
 * * '__IntegrationFlow_AdapterData_FAILED_PROCESSING__' with message processing log error information, attachments, custom header properties and adapter data
 *
 * @param id Id of deployed integration artifact <br>
 * Example: ```IntegrationFlow_MessageStore_COMPLETED_PROCESSING```
 */
public suspend fun HttpClient.getIntegrationRuntimeArtifactsById(id: String, builder: suspend HttpRequestBuilder.() -> Unit = {}): GetIntegrationRuntimeArtifactsById {
  val response = `get`(urlString = """IntegrationRuntimeArtifacts('${id}')""") {
    contentType(Json)
    builder()
  }
  val output = response.body<GetIntegrationRuntimeArtifactsById>()
  return output
}
