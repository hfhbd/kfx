package com.sap.hci.api.client

import com.sap.hci.api.GetValueMappingDesigntimeArtifacts
import com.sap.hci.api.GetValueMappingDesigntimeArtifactsOrderby
import com.sap.hci.api.GetValueMappingDesigntimeArtifactsSelect
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.`get`
import io.ktor.client.request.parameter
import io.ktor.http.isSuccess
import kotlin.Int
import kotlin.Throws
import kotlin.Unit
import odata.Error

/**
 * You can use the following request to get all Value Mappings in the tenant.
 *
 * @param top Show only the first n items.
 * @param skip Skip the first n items.
 * @param select Select properties to be returned.
 * @param orderby Order items by property values, see [Orderby System Query Option](https://www.odata.org/documentation/odata-version-2-0/uri-conventions/). Default order: by 'Name' ascending
 */
@Throws(Error::class)
public suspend fun HttpClient.getValueMappingDesigntimeArtifacts(
  top: Int? = null,
  skip: Int? = null,
  select: GetValueMappingDesigntimeArtifactsSelect? = null,
  orderby: GetValueMappingDesigntimeArtifactsOrderby? = null,
  builder: suspend HttpRequestBuilder.() -> Unit = {},
): GetValueMappingDesigntimeArtifacts {
  val response = `get`(urlString = """ValueMappingDesigntimeArtifacts""") {
    parameter("top", top)
    parameter("skip", skip)
    parameter("select", select)
    parameter("orderby", orderby)
    builder()
  }
  if (response.status.isSuccess()) {
    val output = response.body<GetValueMappingDesigntimeArtifacts>()
    return output
  } else {
    val output = response.body<Error>()
    throw output
  }
}
