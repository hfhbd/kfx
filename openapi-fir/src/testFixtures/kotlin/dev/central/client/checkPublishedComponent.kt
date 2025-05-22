package dev.central.client

import io.ktor.client.HttpClient
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.`get`
import io.ktor.client.request.parameter
import kotlin.String
import kotlin.Unit

/**
 * Check whether a component is published.
 *
 * @param namespace namespace of component
 * @param name name of component
 * @param version version of component
 */
public suspend fun HttpClient.checkPublishedComponent(
  namespace: String? = null,
  name: String? = null,
  version: String? = null,
  builder: suspend HttpRequestBuilder.() -> Unit = {},
) {
  val response = `get`(urlString = """api/v1/publisher/published""") {
    parameter("namespace", namespace)
    parameter("name", name)
    parameter("version", version)
    builder()
  }
}
