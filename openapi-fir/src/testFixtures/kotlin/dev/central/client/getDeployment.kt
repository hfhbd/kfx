package dev.central.client

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.`get`
import kotlin.String
import kotlin.Unit
import kotlinx.io.Source

/**
 * Integrate a deployment bundle with a build for manual testing. For more information, see the the following [documentation](https://central.sonatype.org/publish/publish-portal-api/#manually-testing-a-deployment-bundle).
 *
 * @param deploymentId The deployment identifier, which was obtained by a call to `/api/v1/publisher/upload`.
 * @param relativePath The full path to a specific file from a deployment bundle.
 */
public suspend fun HttpClient.getDeployment(
  deploymentId: String,
  relativePath: String,
  builder: suspend HttpRequestBuilder.() -> Unit = {},
): Source {
  val response = `get`(urlString = """api/v1/publisher/deployment/${deploymentId}/download/${relativePath}""") {
    builder()
  }
  val output = response.body<Source>()
  return output
}
