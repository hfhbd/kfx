package com.sap.hci.api.client

import com.sap.hci.api.GetIntegrationPackagesByIdMessageMappingDesigntimeArtifacts
import com.sap.hci.api.GetIntegrationPackagesByIdMessageMappingDesigntimeArtifactsSelect
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.`get`
import io.ktor.client.request.parameter
import io.ktor.http.isSuccess
import kotlin.String
import kotlin.Throws
import kotlin.Unit
import odata.Error

/**
 * You can use the following request to get all message mappings of an integration package by package Id.
 *
 * @param id ID of integration package <br>
 * Example: ```MessageMappingExamples```
 * @param select Select properties to be returned.
 */
@Throws(Error::class)
public suspend fun HttpClient.getIntegrationPackagesByIdMessageMappingDesigntimeArtifacts(
  id: String,
  select: GetIntegrationPackagesByIdMessageMappingDesigntimeArtifactsSelect? = null,
  builder: suspend HttpRequestBuilder.() -> Unit = {},
): GetIntegrationPackagesByIdMessageMappingDesigntimeArtifacts? {
  val response = `get`(urlString = """IntegrationPackages('${id}')/MessageMappingDesigntimeArtifacts""") {
    parameter("select", select)
    builder()
  }
  if (response.status.value == 404) {
    return null
  }
  if (response.status.isSuccess()) {
    val output = response.body<GetIntegrationPackagesByIdMessageMappingDesigntimeArtifacts>()
    return output
  } else {
    val output = response.body<Error>()
    throw output
  }
}
