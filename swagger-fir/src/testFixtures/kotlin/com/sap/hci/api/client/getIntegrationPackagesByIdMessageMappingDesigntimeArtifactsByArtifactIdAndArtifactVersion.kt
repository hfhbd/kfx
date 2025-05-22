package com.sap.hci.api.client

import com.sap.hci.api.GetIntegrationPackagesByIdMessageMappingDesigntimeArtifactsByArtifactIdAndArtifactVersion
import com.sap.hci.api.GetIntegrationPackagesByIdMessageMappingDesigntimeArtifactsByArtifactIdAndArtifactVersionSelect
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
 * Use the following request to get a message mapping of an integration package by package Id, message mapping Id, and message mapping version.
 *
 * @param id ID of integration package <br>
 * Example: ```MessageMappingExamples```
 * @param artifactId ID of message mapping <br>
 * Example: ```MessageMapping1```
 * @param artifactVersion Version of message mapping <br>
 * You can enter either ```active``` or the version name (e.g. ```2.0.0```) for retrieving the current version.
 * @param select Select properties to be returned.
 */
@Throws(Error::class)
public suspend fun HttpClient.getIntegrationPackagesByIdMessageMappingDesigntimeArtifactsByArtifactIdAndArtifactVersion(
  id: String,
  artifactId: String,
  artifactVersion: String = "active",
  select: GetIntegrationPackagesByIdMessageMappingDesigntimeArtifactsByArtifactIdAndArtifactVersionSelect? = null,
  builder: suspend HttpRequestBuilder.() -> Unit = {},
): GetIntegrationPackagesByIdMessageMappingDesigntimeArtifactsByArtifactIdAndArtifactVersion? {
  val response = `get`(urlString = """IntegrationPackages('${id}')/MessageMappingDesigntimeArtifacts(Id='${artifactId}',Version='${artifactVersion}')""") {
    parameter("select", select)
    builder()
  }
  if (response.status.value == 404) {
    return null
  }
  if (response.status.isSuccess()) {
    val output = response.body<GetIntegrationPackagesByIdMessageMappingDesigntimeArtifactsByArtifactIdAndArtifactVersion>()
    return output
  } else {
    val output = response.body<Error>()
    throw output
  }
}
