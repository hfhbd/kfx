package client

import APIError
import AvailableScopeListEntry
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
public suspend fun HttpClient.clientAvailableScopes(
  serviceInstanceID: String,
  clientName: String,
  realm: String,
  builder: suspend HttpRequestBuilder.() -> Unit = {},
): List<AvailableScopeListEntry> {
  val response = `get`(urlString = """api/v1/instances/${serviceInstanceID}/clients/${realm}/${clientName}/available-scopes""") {
    builder()
  }
  when {
    response.status.isSuccess() -> {
      val output = response.body<List<AvailableScopeListEntry>>()
      return output
    }
    else -> {
        val output = response.body<APIError>()
        throw output
      }
    }
}
