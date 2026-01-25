package client

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.parameter
import io.ktor.client.request.patch
import kotlin.Boolean
import kotlin.String
import kotlin.Unit

/**
 * @param instance_id instance id of instance to provision
 * @param accepts_incomplete asynchronous operations supported
 */
public suspend fun HttpClient.serviceInstanceUpdate(
  instance_id: String,
  accepts_incomplete: Boolean? = null,
  builder: suspend HttpRequestBuilder.() -> Unit = {},
) {
  val response = patch(urlString = """v2/service_instances/${instance_id}""") {
    parameter("accepts_incomplete", accepts_incomplete)
    builder()
  }
  val output = response.body<Unit>()
  return output
}
