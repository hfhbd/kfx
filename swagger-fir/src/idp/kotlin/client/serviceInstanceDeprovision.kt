package client

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.`header`
import io.ktor.client.request.delete
import io.ktor.client.request.parameter
import kotlin.Boolean
import kotlin.String
import kotlin.Unit

/**
 * @param instance_id instance id of instance to provision
 * @param X_Broker_API_Version version number of the Service Broker API that the Platform will use
 * @param X_Broker_API_Originating_Identity identity of the user that initiated the request from the Platform
 * @param X_Broker_API_Request_Identity idenity of the request from the Platform
 * @param accepts_incomplete asynchronous operations supported
 * @param service_id id of the service associated with the instance being deleted
 * @param plan_id id of the plan associated with the instance being deleted
 */
public suspend fun HttpClient.serviceInstanceDeprovision(
  instance_id: String,
  X_Broker_API_Version: String,
  X_Broker_API_Originating_Identity: String,
  X_Broker_API_Request_Identity: String,
  accepts_incomplete: Boolean? = null,
  service_id: String? = null,
  plan_id: String? = null,
  builder: suspend HttpRequestBuilder.() -> Unit = {},
) {
  val response = delete(urlString = """v2/service_instances/${instance_id}""") {
    `header`("X-Broker-API-Version", X_Broker_API_Version)
    `header`("X-Broker-API-Originating-Identity", X_Broker_API_Originating_Identity)
    `header`("X-Broker-API-Request-Identity", X_Broker_API_Request_Identity)
    parameter("accepts_incomplete", accepts_incomplete)
    parameter("service_id", service_id)
    parameter("plan_id", plan_id)
    builder()
  }
  val output = response.body<Unit>()
  return output
}
