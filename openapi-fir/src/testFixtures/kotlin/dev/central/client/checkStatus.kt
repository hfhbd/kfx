package dev.central.client

import dev.central.CheckStatus
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.parameter
import io.ktor.client.request.post
import kotlin.String
import kotlin.Unit

/**
 * Retrieve status of a deployment. Polling this endpoint can be useful for determining when a deployment changes state.
 *
 * @param id The deployment identifier, which was obtained by a call to `/api/v1/publisher/upload`.
 */
public suspend fun HttpClient.checkStatus(id: String? = null, builder: suspend HttpRequestBuilder.() -> Unit = {}): CheckStatus? {
  val response = post(urlString = """api/v1/publisher/status""") {
    parameter("id", id)
    builder()
  }
  if (response.status.value == 404) {
    return null
  }
  val output = response.body<CheckStatus>()
  return output
}
