package com.sap.hci.api.client

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.`get`
import io.ktor.http.ContentType.Application.Json
import io.ktor.http.contentType
import io.ktor.http.isSuccess
import kotlin.String
import kotlin.Throws
import kotlin.Unit
import kotlinx.io.Source
import odata.Error

/**
 * You can use the following request to download an script collection as zip file. Integration flows of configure-only packages cannot be downloaded.<br>For further details, refer to the SAP Help Portal documentation [OData API: Integration Content](https://help.sap.com/viewer/368c481cd6954bdfa5d0435479fd4eaf/Cloud/en-US/d1679a80543f46509a7329243b595bdb.html)</br>
 *
 * @param id Id of script collection artifact <br>
 * Example: **ScriptCollection1**
 * @param version Version of script collection artifact  <br>
 * You can enter either **active** or the version name (e.g. **1.0.2**) for retrieving the current version.
 */
@Throws(Error::class)
public suspend fun HttpClient.getScriptCollectionDesigntimeArtifactsByIdAndVersionValue(
  id: String,
  version: String = "active",
  builder: suspend HttpRequestBuilder.() -> Unit = {},
): Source? {
  val response = `get`(urlString = """ScriptCollectionDesigntimeArtifacts(Id='${id}',Version='${version}')/${'$'}value""") {
    contentType(Json)
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
