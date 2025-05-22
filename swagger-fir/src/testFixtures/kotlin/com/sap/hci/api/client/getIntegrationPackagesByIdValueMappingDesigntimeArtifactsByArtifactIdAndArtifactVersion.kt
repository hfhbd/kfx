package com.sap.hci.api.client

import com.sap.hci.api.ValueMappingDesigntimeArtifact
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.`get`
import kotlin.String
import kotlin.Unit

/**
 * You can use the following request to get a value mapping of an integration package by package Id, valuen mapping and value mapping version.
 *
 * @param id ID of integration package <br>
 * Example: ```ValueMappingExamples```
 * @param artifactId ID of value mapping <br>
 * Example: ```ValueMapping1```
 * @param artifactVersion Version of value mapping <br>
 * You can enter either ```active``` or the version name (e.g. ```2.0.0```) for retrieving the current version.
 */
public suspend fun HttpClient.getIntegrationPackagesByIdValueMappingDesigntimeArtifactsByArtifactIdAndArtifactVersion(
  id: String,
  artifactId: String,
  artifactVersion: String = "active",
  builder: suspend HttpRequestBuilder.() -> Unit = {},
): ValueMappingDesigntimeArtifact? {
  val response = `get`(urlString = """IntegrationPackages('${id}')/ValueMappingDesigntimeArtifacts(Id='${artifactId}',Version='${artifactVersion}')""") {
    builder()
  }
  if (response.status.value == 404) {
    return null
  }
  val output = response.body<ValueMappingDesigntimeArtifact>()
  return output
}
