package client

import APIError
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.`get`
import io.ktor.http.isSuccess
import kotlin.String
import kotlin.Throws
import kotlin.Unit
import kotlin.collections.List

/**
 * @param serviceInstanceID ID of the service instance
 * @param clientName Name of the client (inside the instance)
 * @param realm The realm of the client
 */
@Throws(APIError::class)
public suspend fun HttpClient.clientRoles(
  serviceInstanceID: String,
  clientName: String,
  realm: String,
  builder: suspend HttpRequestBuilder.() -> Unit = {},
): List<String> {
  val response = `get`(urlString = """api/v1/instances/${serviceInstanceID}/clients/${realm}/${clientName}/roles""") {
    builder()
  }
  when {
    response.status.isSuccess() -> {
      val output = response.body<List<String>>()
      return output
    }
    else -> {
      val output = response.body<APIError>()
      throw output
    }
  }
}
