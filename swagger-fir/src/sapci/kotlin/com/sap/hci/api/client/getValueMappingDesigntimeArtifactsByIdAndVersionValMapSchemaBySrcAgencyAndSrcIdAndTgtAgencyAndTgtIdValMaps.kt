package com.sap.hci.api.client

import com.sap.hci.api.GetValueMappingDesigntimeArtifactsByIdAndVersionValMapSchemaBySrcAgencyAndSrcIdAndTgtAgencyAndTgtIdValMaps
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
 * You can use the following request to get all the value mappings for a specific bidirectional agency identifier.
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
 * @param filter Returns a subset of the entries, which matches the filter condition.<br>
 * Example: ```Value/SrcValue eq 'SourceValue' and Value/TgtValue eq 'TargetValue'``` provides all the value mappings based on the source and target value for a specific bidirectional agency identifier.
 */
@Throws(Error::class)
public suspend fun HttpClient.getValueMappingDesigntimeArtifactsByIdAndVersionValMapSchemaBySrcAgencyAndSrcIdAndTgtAgencyAndTgtIdValMaps(
  id: String,
  version: String = "active",
  srcAgency: String,
  srcId: String,
  tgtAgency: String,
  tgtId: String,
  filter: String? = null,
  builder: suspend HttpRequestBuilder.() -> Unit = {},
): GetValueMappingDesigntimeArtifactsByIdAndVersionValMapSchemaBySrcAgencyAndSrcIdAndTgtAgencyAndTgtIdValMaps? {
  val response = `get`(urlString = """ValueMappingDesigntimeArtifacts(Id='${id}',Version='${version}')/ValMapSchema(SrcAgency='${srcAgency}',SrcId='${srcId}',TgtAgency='${tgtAgency}',TgtId='${tgtId}')/ValMaps""") {
    parameter("filter", filter)
    builder()
  }
  if (response.status.value == 404) {
    return null
  }
  if (response.status.isSuccess()) {
    val output = response.body<GetValueMappingDesigntimeArtifactsByIdAndVersionValMapSchemaBySrcAgencyAndSrcIdAndTgtAgencyAndTgtIdValMaps>()
    return output
  } else {
    val output = response.body<Error>()
    throw output
  }
}
