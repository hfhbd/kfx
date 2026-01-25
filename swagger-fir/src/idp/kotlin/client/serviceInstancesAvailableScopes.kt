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
 */
@Throws(APIError::class)
public suspend fun HttpClient.serviceInstancesAvailableScopes(serviceInstanceID: String, builder: suspend HttpRequestBuilder.() -> Unit = {}): List<AvailableScopeListEntry> {
  val response = `get`(urlString = """api/v1/instances/${serviceInstanceID}/available-scopes""") {
    builder()
  }
  if (response.status.isSuccess()) {
    val output = response.body<List<AvailableScopeListEntry>>()
    return output
  } else {
    val output = response.body<APIError>()
    throw output
  }
}
