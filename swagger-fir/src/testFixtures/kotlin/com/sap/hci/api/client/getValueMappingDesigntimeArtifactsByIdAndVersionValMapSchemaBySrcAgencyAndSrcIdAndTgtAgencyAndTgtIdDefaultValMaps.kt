package com.sap.hci.api.client

import com.sap.hci.api.GetValueMappingDesigntimeArtifactsByIdAndVersionValMapSchemaBySrcAgencyAndSrcIdAndTgtAgencyAndTgtIdDefaultValMaps
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
 * You can use the following request to get all the default value mappings for a specific bidirectional agency identifier.
 *
 * @param id Id of integration artifact Value mapping<br>
 * Example: ```ValueMapping1```
 * @param version Version of the integration artifact Value Mapping<br>
 * Example: ```active```
 * @param srcAgency Source Agency<br>
 * Example: ```SourceAgency```
 * @param srcId Source Id<br>
 * Example: ```SrcId```
 * @param tgtAgency Target Agency<br>
 * Example: ```TargetAgency```
 * @param tgtId Target Id<br>
 * Example: ```TgtId```
 */
@Throws(Error::class)
public suspend fun HttpClient.getValueMappingDesigntimeArtifactsByIdAndVersionValMapSchemaBySrcAgencyAndSrcIdAndTgtAgencyAndTgtIdDefaultValMaps(
  id: String,
  version: String = "active",
  srcAgency: String,
  srcId: String,
  tgtAgency: String,
  tgtId: String,
  builder: suspend HttpRequestBuilder.() -> Unit = {},
): GetValueMappingDesigntimeArtifactsByIdAndVersionValMapSchemaBySrcAgencyAndSrcIdAndTgtAgencyAndTgtIdDefaultValMaps? {
  val response = `get`(urlString = """ValueMappingDesigntimeArtifacts(Id='${id}',Version='${version}')/ValMapSchema(SrcAgency='${srcAgency}',SrcId='${srcId}',TgtAgency='${tgtAgency}',TgtId='${tgtId}')/DefaultValMaps""") {
    builder()
  }
  if (response.status.value == 404) {
    return null
  }
  if (response.status.isSuccess()) {
    val output = response.body<GetValueMappingDesigntimeArtifactsByIdAndVersionValMapSchemaBySrcAgencyAndSrcIdAndTgtAgencyAndTgtIdDefaultValMaps>()
    return output
  } else {
    val output = response.body<Error>()
    throw output
  }
}
