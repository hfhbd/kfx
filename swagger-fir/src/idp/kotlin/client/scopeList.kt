package client

import APIError
import Scope
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
public suspend fun HttpClient.scopeList(serviceInstanceID: String, builder: suspend HttpRequestBuilder.() -> Unit = {}): List<Scope> {
  val response = `get`(urlString = """api/v1/instances/${serviceInstanceID}/scopes""") {
    builder()
  }
  when {
    response.status.isSuccess() -> {
      val output = response.body<List<Scope>>()
      return output
    }

    else -> {
      val output = response.body<APIError>()
      throw output
    }
  }
}
