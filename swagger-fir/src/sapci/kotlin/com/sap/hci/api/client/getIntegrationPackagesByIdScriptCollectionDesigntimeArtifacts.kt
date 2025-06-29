package com.sap.hci.api.client

import com.sap.hci.api.GetIntegrationPackagesByIdScriptCollectionDesigntimeArtifacts
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.`get`
import io.ktor.http.isSuccess
import kotlin.String
import kotlin.Throws
import kotlin.Unit
import odata.Error

/**
 * You can use the following request to get all script collections of an integration package by package Id.
 *
 * @param id Id of integration package <br>
 * Example: ```packageName```
 */
@Throws(Error::class)
public suspend fun HttpClient.getIntegrationPackagesByIdScriptCollectionDesigntimeArtifacts(id: String, builder: suspend HttpRequestBuilder.() -> Unit = {}): GetIntegrationPackagesByIdScriptCollectionDesigntimeArtifacts? {
  val response = `get`(urlString = """IntegrationPackages('${id}')/ScriptCollectionDesigntimeArtifacts""") {
    builder()
  }
  if (response.status.value == 404) {
    return null
  }
  if (response.status.isSuccess()) {
    val output = response.body<GetIntegrationPackagesByIdScriptCollectionDesigntimeArtifacts>()
    return output
  } else {
    val output = response.body<Error>()
    throw output
  }
}
