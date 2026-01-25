package client

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.delete
import io.ktor.client.request.parameter
import kotlin.Boolean
import kotlin.String
import kotlin.Unit

/**
 * @param instance_id instance id of instance to provision
 * @param accepts_incomplete asynchronous operations supported
 * @param service_id id of the service associated with the instance being deleted
 * @param plan_id id of the plan associated with the instance being deleted
 */
public suspend fun HttpClient.serviceInstanceDeprovision(
  instance_id: String,
  accepts_incomplete: Boolean? = null,
  service_id: String? = null,
  plan_id: String? = null,
  builder: suspend HttpRequestBuilder.() -> Unit = {},
) {
  val response = delete(urlString = """v2/service_instances/${instance_id}""") {
    parameter("accepts_incomplete", accepts_incomplete)
    parameter("service_id", service_id)
    parameter("plan_id", plan_id)
    builder()
  }
  val output = response.body<Unit>()
  return output
}
