package com.sap.hci.api.client

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.`get`
import io.ktor.http.ContentType.Application.Json
import io.ktor.http.contentType
import io.ktor.http.isSuccess
import kotlin.String
import kotlin.Throws
import kotlin.Unit
import kotlinx.io.Source
import odata.Error

/**
 * You can use the following request to download a value mapping as zip file. Value mappings of configure-only packages cannot be downloaded. 
 * In API sandbox, the following integration flows with Id and version are available:
 * * '__IntegrationFlow_FAILED_DEPLOYMENT__' with version '__1.0.0__' 
 * * '__IntegrationFlow_MessageStore_COMPLETED_PROCESSING__' with version '__1.0.0__'
 * * '__IntegrationFlow_AdapterData_FAILED_PROCESSING__' with version '__1.0.0__'
 * * '__IntegrationFlowWithConfiguration__' with version '__1.0.5__'
 *
 * @param id Id of the value mapping <br>
 * Example: ```ValueMapping1```
 * @param version Version of the value mapping  <br>
 * You can enter either ```active``` or the version name (e.g. ```1.0.5```) for retrieving the current version.
 */
@Throws(Error::class)
public suspend fun HttpClient.getValueMappingDesigntimeArtifactsByIdAndVersionValue(
  id: String,
  version: String = "active",
  builder: suspend HttpRequestBuilder.() -> Unit = {},
): Source {
  val response = `get`(urlString = """ValueMappingDesigntimeArtifacts(Id='${id}',Version='${version}')/${'$'}value""") {
    contentType(Json)
    builder()
  }
  if (response.status.isSuccess()) {
    val output = response.body<Source>()
    return output
  } else {
    val output = response.body<Error>()
    throw output
  }
}
