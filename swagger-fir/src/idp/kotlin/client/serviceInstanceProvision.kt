package client

import ServiceInstanceProvisionResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.parameter
import io.ktor.client.request.put
import kotlin.Boolean
import kotlin.String
import kotlin.Unit

/**
 * @param X_Broker_API_Version version number of the Service Broker API that the Platform will use
 * @param X_Broker_API_Originating_Identity identity of the user that initiated the request from the Platform
 * @param X_Broker_API_Request_Identity idenity of the request from the Platform
 * @param instance_id instance id of instance to provision
 * @param accepts_incomplete asynchronous operations supported
 */
public suspend fun HttpClient.serviceInstanceProvision(
  X_Broker_API_Version: String,
  X_Broker_API_Originating_Identity: String,
  X_Broker_API_Request_Identity: String,
  instance_id: String,
  accepts_incomplete: Boolean? = null,
  builder: suspend HttpRequestBuilder.() -> Unit = {},
): ServiceInstanceProvisionResponse {
  val response = put(urlString = """v2/service_instances/${instance_id}""") {
    parameter("accepts_incomplete", accepts_incomplete)
    builder()
  }
  val output = response.body<ServiceInstanceProvisionResponse>()
  return output
}
