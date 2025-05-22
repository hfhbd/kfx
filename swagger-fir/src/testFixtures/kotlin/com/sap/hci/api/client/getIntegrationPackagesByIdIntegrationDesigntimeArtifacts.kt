package com.sap.hci.api.client

import com.sap.hci.api.GetIntegrationPackagesByIdIntegrationDesigntimeArtifacts
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.`get`
import kotlin.String
import kotlin.Unit

/**
 * You can use the following request to get all integration flows of an integration package by package Id.
 *
 * @param id ID of integration package <br>
 * Example: ```ERPtoSuccessFactorsEmployeeCentralEmployeeandOrganizationalData```
 */
public suspend fun HttpClient.getIntegrationPackagesByIdIntegrationDesigntimeArtifacts(id: String, builder: suspend HttpRequestBuilder.() -> Unit = {}): GetIntegrationPackagesByIdIntegrationDesigntimeArtifacts? {
  val response = `get`(urlString = """IntegrationPackages('${id}')/IntegrationDesigntimeArtifacts""") {
    builder()
  }
  if (response.status.value == 404) {
    return null
  }
  val output = response.body<GetIntegrationPackagesByIdIntegrationDesigntimeArtifacts>()
  return output
}
