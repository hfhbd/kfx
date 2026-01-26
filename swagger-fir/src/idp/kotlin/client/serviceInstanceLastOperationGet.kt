package client

import LastOperationResource
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.`get`
import io.ktor.client.request.`header`
import io.ktor.client.request.parameter
import io.ktor.http.ContentType.Application.Json
import io.ktor.http.contentType
import kotlin.String
import kotlin.Unit

/**
 * @param instance_id instance id of instance to provision
 * @param X_Broker_API_Version version number of the Service Broker API that the Platform will use
 * @param X_Broker_API_Originating_Identity identity of the user that initiated the request from the Platform
 * @param X_Broker_API_Request_Identity identity of the request from the Platform
 * @param service_id id of the service associated with the instance
 * @param plan_id id of the plan associated with the instance
 * @param operation a provided identifier for the operation
 */
public suspend fun HttpClient.serviceInstanceLastOperationGet(
  instance_id: String,
  X_Broker_API_Version: String,
  X_Broker_API_Originating_Identity: String,
  X_Broker_API_Request_Identity: String,
  service_id: String? = null,
  plan_id: String? = null,
  operation: String? = null,
  builder: suspend HttpRequestBuilder.() -> Unit = {},
): LastOperationResource? {
  val response = `get`(urlString = """v2/service_instances/${instance_id}/last_operation""") {
    `header`("X-Broker-API-Version", X_Broker_API_Version)
    `header`("X-Broker-API-Originating-Identity", X_Broker_API_Originating_Identity)
    `header`("X-Broker-API-Request-Identity", X_Broker_API_Request_Identity)
    parameter("service_id", service_id)
    parameter("plan_id", plan_id)
    parameter("operation", operation)
    contentType(Json)
    builder()
  }
  if (response.status.value == 404) {
    return null
  }
  val output = response.body<LastOperationResource>()
  return output
}
