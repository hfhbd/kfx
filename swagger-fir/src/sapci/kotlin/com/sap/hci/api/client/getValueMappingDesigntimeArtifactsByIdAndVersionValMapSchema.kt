package com.sap.hci.api.client

import com.sap.hci.api.GetValueMappingDesigntimeArtifactsByIdAndVersionValMapSchema
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.`get`
import io.ktor.client.request.parameter
import io.ktor.http.isSuccess
import kotlin.String
import kotlin.Throws
import kotlin.Unit
import odata.Error

/**
 * You can use the following request to get all bidirectional agency identifiers in the Value Mapping.
 *
 * @param id Id of integration artifact <br>
 * Example: ```ValueMapping1```
 * @param version Version of the integration artifact<br>
 * Example: ```active```
 * @param filter Returns a subset of the entries, which matches the filter condition. The only supported expression is:.<br>
 * ```State eq 'Configured'```<br>
 *  It provides all the bidirectional agency identifiers that are in configured state.
 */
@Throws(Error::class)
public suspend fun HttpClient.getValueMappingDesigntimeArtifactsByIdAndVersionValMapSchema(
  id: String,
  version: String = "active",
  filter: String? = null,
  builder: suspend HttpRequestBuilder.() -> Unit = {},
): GetValueMappingDesigntimeArtifactsByIdAndVersionValMapSchema? {
  val response = `get`(urlString = """ValueMappingDesigntimeArtifacts(Id='${id}',Version='${version}')/ValMapSchema""") {
    parameter("filter", filter)
    builder()
  }
  if (response.status.value == 404) {
    return null
  }
  if (response.status.isSuccess()) {
    val output = response.body<GetValueMappingDesigntimeArtifactsByIdAndVersionValMapSchema>()
    return output
  } else {
    val output = response.body<Error>()
    throw output
  }
}
