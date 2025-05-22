package com.sap.hci.api.client

import com.sap.hci.api.GetMessageMappingDesigntimeArtifacts
import com.sap.hci.api.GetMessageMappingDesigntimeArtifactsOrderby
import com.sap.hci.api.GetMessageMappingDesigntimeArtifactsSelect
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
 * You can use the following request to get all message mappings deployed on the tenant.
 *
 * @param top Show only the first n items.
 * @param skip Skip the first n items.
 * @param select Select properties to be returned.
 * @param orderby Order items by property values, see [Orderby System Query Option](https://www.odata.org/documentation/odata-version-2-0/uri-conventions/). Default order: by 'Name' ascending
 */
@Throws(Error::class)
public suspend fun HttpClient.getMessageMappingDesigntimeArtifacts(
  top: Int? = null,
  skip: Int? = null,
  select: GetMessageMappingDesigntimeArtifactsSelect? = null,
  orderby: GetMessageMappingDesigntimeArtifactsOrderby? = null,
  builder: suspend HttpRequestBuilder.() -> Unit = {},
): GetMessageMappingDesigntimeArtifacts {
  val response = `get`(urlString = """MessageMappingDesigntimeArtifacts""") {
    parameter("top", top)
    parameter("skip", skip)
    parameter("select", select)
    parameter("orderby", orderby)
    builder()
  }
  if (response.status.isSuccess()) {
    val output = response.body<GetMessageMappingDesigntimeArtifacts>()
    return output
  } else {
    val output = response.body<Error>()
    throw output
  }
}
