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
 * @param binding_id binding id of binding to create
 * @param X_Broker_API_Version version number of the Service Broker API that the Platform will use
 * @param X_Broker_API_Originating_Identity identity of the user that initiated the request from the Platform
 * @param X_Broker_API_Request_Identity idenity of the request from the Platform
 * @param service_id id of the service associated with the instance being deleted
 * @param plan_id id of the plan associated with the instance being deleted
 * @param accepts_incomplete asynchronous operations supported
 */
public suspend fun HttpClient.serviceBindingUnbinding(
  instance_id: String,
  binding_id: String,
  X_Broker_API_Version: String,
  X_Broker_API_Originating_Identity: String,
  X_Broker_API_Request_Identity: String,
  service_id: String? = null,
  plan_id: String? = null,
  accepts_incomplete: Boolean? = null,
  builder: suspend HttpRequestBuilder.() -> Unit = {},
) {
  val response = delete(urlString = """v2/service_instances/${instance_id}/service_bindings/${binding_id}""") {
    `header`("X-Broker-API-Version", X_Broker_API_Version)
    `header`("X-Broker-API-Originating-Identity", X_Broker_API_Originating_Identity)
    `header`("X-Broker-API-Request-Identity", X_Broker_API_Request_Identity)
    parameter("service_id", service_id)
    parameter("plan_id", plan_id)
    parameter("accepts_incomplete", accepts_incomplete)
    builder()
  }
  val output = response.body<Unit>()
  return output
}
