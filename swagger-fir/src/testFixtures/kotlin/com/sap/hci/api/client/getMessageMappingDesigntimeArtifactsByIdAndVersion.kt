package com.sap.hci.api.client

import com.sap.hci.api.GetMessageMappingDesigntimeArtifactsByIdAndVersion
import com.sap.hci.api.GetMessageMappingDesigntimeArtifactsByIdAndVersionSelect
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
 * You can use the following request to get a deployed message mapping deployed by Id and version.
 *
 * @param id Id of message mapping <br>
 * Example: ```MessageMapping1```
 * @param version Version of message mapping <br>
 * You can enter either ```active``` or the version name (e.g. ```1.0.5```) for retrieving the current version.
 * @param select Select properties to be returned.
 */
public suspend fun HttpClient.getMessageMappingDesigntimeArtifactsByIdAndVersion(
  id: String,
  version: String = "active",
  select: GetMessageMappingDesigntimeArtifactsByIdAndVersionSelect? = null,
  builder: suspend HttpRequestBuilder.() -> Unit = {},
): GetMessageMappingDesigntimeArtifactsByIdAndVersion? {
  val response = `get`(urlString = """MessageMappingDesigntimeArtifacts(Id='${id}',Version='${version}')""") {
    parameter("select", select)
    contentType(Json)
    builder()
  }
  if (response.status.value == 404) {
    return null
  }
  val output = response.body<GetMessageMappingDesigntimeArtifactsByIdAndVersion>()
  return output
}
