package com.sap.hci.api.client

import com.sap.hci.api.GetIntegrationPackagesByIdCustomTagsOrderby
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.`get`
import io.ktor.client.request.parameter
import io.ktor.http.isSuccess
import kotlin.Int
import kotlin.String
import kotlin.Throws
import kotlin.Unit
import odata.Error

/**
 * You can use the following request to get custom tags of an integration package by package Id.
 *
 * @param id ID of integration package <br>
 * Example: ```CloudPlatformAPITestPackage```
 * @param top Show only the first n items.
 * @param skip Skip the first n items.
 * @param orderby Order items by property values, see [Orderby System Query Option](https://www.odata.org/documentation/odata-version-2-0/uri-conventions/)
 */
@Throws(Error::class)
public suspend fun HttpClient.getIntegrationPackagesByIdCustomTags(
  id: String,
  top: Int? = null,
  skip: Int? = null,
  orderby: GetIntegrationPackagesByIdCustomTagsOrderby? = null,
  builder: suspend HttpRequestBuilder.() -> Unit = {},
) {
  val response = `get`(urlString = """IntegrationPackages('${id}')/CustomTags""") {
    parameter("top", top)
    parameter("skip", skip)
    parameter("orderby", orderby)
    builder()
  }
  if (response.status.isSuccess()) {
  } else {
    val output = response.body<Error>()
    throw output
  }
}
