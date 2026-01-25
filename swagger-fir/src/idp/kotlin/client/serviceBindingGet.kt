package client

import ServiceBindingResource
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.`get`
import io.ktor.client.request.parameter
import kotlin.String
import kotlin.Unit

/**
 * @param X_Broker_API_Version version number of the Service Broker API that the Platform will use
 * @param X_Broker_API_Originating_Identity identity of the user that initiated the request from the Platform
 * @param X_Broker_API_Request_Identity idenity of the request from the Platform
 * @param instance_id instance id of instance to provision
 * @param binding_id binding id of binding to create
 * @param service_id id of the service associated with the instance
 * @param plan_id id of the plan associated with the instance
 */
public suspend fun HttpClient.serviceBindingGet(
  X_Broker_API_Version: String,
  X_Broker_API_Originating_Identity: String,
  X_Broker_API_Request_Identity: String,
  instance_id: String,
  binding_id: String,
  service_id: String? = null,
  plan_id: String? = null,
  builder: suspend HttpRequestBuilder.() -> Unit = {},
): ServiceBindingResource? {
  val response = `get`(urlString = """v2/service_instances/${instance_id}/service_bindings/${binding_id}""") {
    parameter("service_id", service_id)
    parameter("plan_id", plan_id)
    builder()
  }
  if (response.status.value == 404) {
    return null
  }
  val output = response.body<ServiceBindingResource>()
  return output
}
