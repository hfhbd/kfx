package client

import Catalog
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.`get`
import kotlin.String
import kotlin.Unit

/**
 * @param X_Broker_API_Version version number of the Service Broker API that the Platform will use
 * @param X_Broker_API_Originating_Identity identity of the user that initiated the request from the Platform
 * @param X_Broker_API_Request_Identity idenity of the request from the Platform
 */
public suspend fun HttpClient.catalogGet(
  X_Broker_API_Version: String,
  X_Broker_API_Originating_Identity: String,
  X_Broker_API_Request_Identity: String,
  builder: suspend HttpRequestBuilder.() -> Unit = {},
): Catalog {
  val response = `get`(urlString = """v2/catalog""") {
    builder()
  }
  val output = response.body<Catalog>()
  return output
}
