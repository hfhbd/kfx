package com.sap.hci.api.client

import com.sap.hci.api.GetValueMappingDesigntimeArtifactsByIdAndVersion
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
 * You can use the following request to get a specific Value Mapping in the tenant.
 *
 * @param id Id of the value mapping <br>
 * Example: ```ValueMapping1```
 * @param version Version of the value mapping <br>
 * Example: ```active```
 */
@Throws(Error::class)
public suspend fun HttpClient.getValueMappingDesigntimeArtifactsByIdAndVersion(
  id: String,
  version: String = "active",
  builder: suspend HttpRequestBuilder.() -> Unit = {},
): GetValueMappingDesigntimeArtifactsByIdAndVersion? {
  val response = `get`(urlString = """ValueMappingDesigntimeArtifacts(Id='${id}',Version='${version}')""") {
    builder()
  }
  if (response.status.value == 404) {
    return null
  }
  if (response.status.isSuccess()) {
    val output = response.body<GetValueMappingDesigntimeArtifactsByIdAndVersion>()
    return output
  } else {
    val output = response.body<Error>()
    throw output
  }
}
