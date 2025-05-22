package dev.central.client

import io.ktor.client.HttpClient
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.post
import kotlin.String
import kotlin.Unit

/**
 * Publish a deployment. Deployments can be published if they are in a `VALIDATED` state.
 *
 * @param deploymentId The deployment identifier, which was obtained by a call to `/api/v1/publisher/upload`.
 */
public suspend fun HttpClient.createDeployment(deploymentId: String, builder: suspend HttpRequestBuilder.() -> Unit = {}) {
  val response = post(urlString = """api/v1/publisher/deployment/${deploymentId}""") {
    builder()
  }
}
