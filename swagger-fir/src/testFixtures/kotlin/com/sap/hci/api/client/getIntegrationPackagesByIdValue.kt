package com.sap.hci.api.client

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.`get`
import io.ktor.http.isSuccess
import kotlin.String
import kotlin.Throws
import kotlin.Unit
import kotlinx.io.Source
import odata.Error

/**
 * You can use the following request to download an integration package of as .zip file. Download fails if the package contains one or more artifacts in draft state.
 *
 * @param id ID of integration package <br>
 * Example: ```ERPtoSuccessFactorsEmployeeCentralEmployeeandOrganizationalData```
 */
@Throws(Error::class)
public suspend fun HttpClient.getIntegrationPackagesByIdValue(id: String, builder: suspend HttpRequestBuilder.() -> Unit = {}): Source? {
  val response = `get`(urlString = """IntegrationPackages('${id}')/${'$'}value""") {
    builder()
  }
  if (response.status.value == 404) {
    return null
  }
  if (response.status.isSuccess()) {
    val output = response.body<Source>()
    return output
  } else {
    val output = response.body<Error>()
    throw output
  }
}
