package com.sap.hci.api.client

import com.sap.hci.api.GetIntegrationPackagesByIdValueMappingDesigntimeArtifacts
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.`get`
import io.ktor.http.HttpStatusCode.Companion.NotFound
import kotlin.String
import kotlin.Unit

/**
 * You can use the following request to get all value mappings of an integration package by package Id.
 *
 * @param id ID of integration package <br>
 * Example: ```ValueMappingExamples```
 */
public suspend fun HttpClient.getIntegrationPackagesByIdValueMappingDesigntimeArtifacts(id: String, builder: suspend HttpRequestBuilder.() -> Unit = {}): GetIntegrationPackagesByIdValueMappingDesigntimeArtifacts? {
  val response = `get`(urlString = """IntegrationPackages('${id}')/ValueMappingDesigntimeArtifacts""") {
    builder()
  }
  when {
    response.status == NotFound -> {
      return null
    }
  }
  val output = response.body<GetIntegrationPackagesByIdValueMappingDesigntimeArtifacts>()
  return output
}
