package client

import APIError
import Scope
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.put
import io.ktor.client.request.setBody
import io.ktor.http.ContentType.Application.Json
import io.ktor.http.contentType
import io.ktor.http.isSuccess
import kotlin.String
import kotlin.Throws
import kotlin.Unit

/**
 * @param serviceInstanceID ID of the service instance
 * @param realm The realm of the client
 * @param scopeName Name of the scope (inside the instance)
 */
@Throws(APIError::class)
public suspend fun HttpClient.scopePut(
  input: Scope,
  serviceInstanceID: String,
  realm: String,
  scopeName: String,
  builder: suspend HttpRequestBuilder.() -> Unit = {},
) {
  val response = put(urlString = """api/v1/instances/${serviceInstanceID}/scopes/${realm}/${scopeName}""") {
    contentType(Json)
    setBody(input)
    builder()
  }
  if (response.status.isSuccess()) {
    val output = response.body<Unit>()
    return output
  } else {
    val output = response.body<APIError>()
    throw output
  }
}
