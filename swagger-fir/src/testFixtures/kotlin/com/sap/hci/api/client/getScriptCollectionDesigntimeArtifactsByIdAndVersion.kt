package com.sap.hci.api.client

import com.sap.hci.api.GetScriptCollectionDesigntimeArtifactsByIdAndVersion
import com.sap.hci.api.GetScriptCollectionDesigntimeArtifactsByIdAndVersionSelect
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.`get`
import io.ktor.client.request.parameter
import io.ktor.http.ContentType.Application.Json
import io.ktor.http.contentType
import kotlin.String
import kotlin.Unit

/**
 * You can use the following request to get a script collection flow by Id and version.<br>For further details, refer to the SAP Help Portal documentation [OData API: Integration Content](https://help.sap.com/viewer/368c481cd6954bdfa5d0435479fd4eaf/Cloud/en-US/d1679a80543f46509a7329243b595bdb.html)</br>
 *
 * @param id Id of script collection <br>
 * Example: **ScriptCollection1**
 * @param version Version of a script collection artifact <br>
 * You can enter either **active** or the version name (e.g. **1.0.2**) for retrieving the current version.
 * @param select Select properties to be returned.
 */
public suspend fun HttpClient.getScriptCollectionDesigntimeArtifactsByIdAndVersion(
  id: String,
  version: String = "active",
  select: GetScriptCollectionDesigntimeArtifactsByIdAndVersionSelect? = null,
  builder: suspend HttpRequestBuilder.() -> Unit = {},
): GetScriptCollectionDesigntimeArtifactsByIdAndVersion? {
  val response = `get`(urlString = """ScriptCollectionDesigntimeArtifacts(Id='${id}',Version='${version}')""") {
    parameter("select", select)
    contentType(Json)
    builder()
  }
  if (response.status.value == 404) {
    return null
  }
  val output = response.body<GetScriptCollectionDesigntimeArtifactsByIdAndVersion>()
  return output
}
