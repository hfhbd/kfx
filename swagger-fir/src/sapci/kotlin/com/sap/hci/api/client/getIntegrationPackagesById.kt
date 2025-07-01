package com.sap.hci.api.client

import com.sap.hci.api.IntegrationPackage
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.`get`
import kotlin.String
import kotlin.Unit

/**
 * You can use the following request to get an integration packages by Id.
 *
 * @param id ID of integration package <br>
 * Example: ```ERPtoSuccessFactorsEmployeeCentralEmployeeandOrganizationalData```
 */
public suspend fun HttpClient.getIntegrationPackagesById(id: String, builder: suspend HttpRequestBuilder.() -> Unit = {}): IntegrationPackage? {
  val response = `get`(urlString = """IntegrationPackages('${id}')""") {
    builder()
  }
  if (response.status.value == 404) {
    return null
  }
  val output = response.body<IntegrationPackage>()
  return output
}
