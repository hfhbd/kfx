package com.sap.hci.api.client

import com.sap.hci.api.GetIntegrationPackages
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.`get`
import io.ktor.client.request.parameter
import kotlin.Int
import kotlin.String
import kotlin.Unit

/**
 * You can use the following request to get all integration packages.
 *
 * @param author Custom Tag property defined in the tenant settings. You can provide multiple values.<br>
 * Example1: ```Author=Fred```
 * Example2: ```Author=Fred|John```
 * @param loB Custom Tag property defined in the tenant settings. You can provide multiple values.<br>
 * Example1: ```LoB=Sales```
 * Example2: ```LoB=Sales|Marketing```
 * @param top Show only the first n items.
 * @param skip Skip the first n items.
 */
public suspend fun HttpClient.getIntegrationPackages(
  author: String? = null,
  loB: String? = null,
  top: Int? = null,
  skip: Int? = null,
  builder: suspend HttpRequestBuilder.() -> Unit = {},
): GetIntegrationPackages {
  val response = `get`(urlString = """IntegrationPackages""") {
    parameter("author", author)
    parameter("loB", loB)
    parameter("top", top)
    parameter("skip", skip)
    builder()
  }
  val output = response.body<GetIntegrationPackages>()
  return output
}
