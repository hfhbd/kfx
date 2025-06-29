package com.sap.hci.api.client

import com.sap.hci.api.ScriptCollectionDesigntimeArtifact
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
 * You can use the following request to get script collection of an integration package by package Id, script collection Id and script collection version.
 *
 * @param id Id of integration package <br>
 * Example: ```packageName```
 * @param artifactId Id of script collection <br>
 * Example: ```ScriptCollectionArtifactID```
 * @param artifactVersion Version of script collection <br>
 * You can enter either ```active``` or the version name (e.g. ```2.0.0```) for retrieving the current version.
 */
@Throws(Error::class)
public suspend fun HttpClient.getIntegrationPackagesByIdScriptCollectionDesigntimeArtifactsByArtifactIdAndArtifactVersion(
  id: String,
  artifactId: String,
  artifactVersion: String = "active",
  builder: suspend HttpRequestBuilder.() -> Unit = {},
): ScriptCollectionDesigntimeArtifact? {
  val response = `get`(urlString = """IntegrationPackages('${id}')/ScriptCollectionDesigntimeArtifacts(Id='${artifactId}',Version='${artifactVersion}')""") {
    builder()
  }
  if (response.status.value == 404) {
    return null
  }
  if (response.status.isSuccess()) {
    val output = response.body<ScriptCollectionDesigntimeArtifact>()
    return output
  } else {
    val output = response.body<Error>()
    throw output
  }
}
