package client

import ServiceInstanceResource
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.`get`
import io.ktor.client.request.parameter
import kotlin.String
import kotlin.Unit

/**
 * @param instance_id instance id of instance to provision
 * @param service_id id of the service associated with the instance
 * @param plan_id id of the plan associated with the instance
 */
public suspend fun HttpClient.serviceInstanceGet(
  instance_id: String,
  service_id: String? = null,
  plan_id: String? = null,
  builder: suspend HttpRequestBuilder.() -> Unit = {},
): ServiceInstanceResource? {
  val response = `get`(urlString = """v2/service_instances/${instance_id}""") {
    parameter("service_id", service_id)
    parameter("plan_id", plan_id)
    builder()
  }
  if (response.status.value == 404) {
    return null
  }
  val output = response.body<ServiceInstanceResource>()
  return output
}
