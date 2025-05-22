package dev.central.client

import io.ktor.client.HttpClient
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.delete
import kotlin.String
import kotlin.Unit

/**
 * Drop a deployment. Deployments can be dropped if they are in a `FAILED` or `VALIDATED` state.
 *
 * @param deploymentId The deployment identifier, which was obtained by a call to `/api/v1/publisher/upload`.
 */
public suspend fun HttpClient.deleteDeployment(deploymentId: String, builder: suspend HttpRequestBuilder.() -> Unit = {}) {
  val response = delete(urlString = """api/v1/publisher/deployment/${deploymentId}""") {
    builder()
  }
}
