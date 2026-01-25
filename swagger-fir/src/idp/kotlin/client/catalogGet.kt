package client

import Catalog
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.`get`
import io.ktor.client.request.`header`
import kotlin.String
import kotlin.Unit

/**
 * @param X_Broker_API_Version version number of the Service Broker API that the Platform will use
 * @param X_Broker_API_Originating_Identity identity of the user that initiated the request from the Platform
 * @param X_Broker_API_Request_Identity identity of the request from the Platform
 */
public suspend fun HttpClient.catalogGet(
  X_Broker_API_Version: String,
  X_Broker_API_Originating_Identity: String,
  X_Broker_API_Request_Identity: String,
  builder: suspend HttpRequestBuilder.() -> Unit = {},
): Catalog {
  val response = `get`(urlString = """v2/catalog""") {
    `header`("X-Broker-API-Version", X_Broker_API_Version)
    `header`("X-Broker-API-Originating-Identity", X_Broker_API_Originating_Identity)
    `header`("X-Broker-API-Request-Identity", X_Broker_API_Request_Identity)
    builder()
  }
  val output = response.body<Catalog>()
  return output
}
