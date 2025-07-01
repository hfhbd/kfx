package com.sap.hci.api.client

import com.sap.hci.api.IntegrationDesigntimeArtifact
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.`get`
import kotlin.String
import kotlin.Unit

/**
 * You can use the following request to get an integration flow of an integration package by package Id, integration flow and integration flow version.
 *
 * @param id ID of integration package <br>
 * Example: ```ERPtoSuccessFactorsEmployeeCentralEmployeeandOrganizationalData```
 * @param artifactId ID of integration flow <br>
 * Example: ```com.sap.PA_SE_IN.erp2ec.SAPtoSFSFGenericODataUpsert.v1```
 * @param artifactVersion Version of integration flow <br>
 * You can enter either ```active``` or the version name (e.g. ```8.1.0```) for retrieving the current version.
 */
public suspend fun HttpClient.getIntegrationPackagesByIdIntegrationDesigntimeArtifactsByArtifactIdAndArtifactVersion(
  id: String,
  artifactId: String,
  artifactVersion: String = "active",
  builder: suspend HttpRequestBuilder.() -> Unit = {},
): IntegrationDesigntimeArtifact? {
  val response = `get`(urlString = """IntegrationPackages('${id}')/IntegrationDesigntimeArtifacts(Id='${artifactId}',Version='${artifactVersion}')""") {
    builder()
  }
  if (response.status.value == 404) {
    return null
  }
  val output = response.body<IntegrationDesigntimeArtifact>()
  return output
}
