package dev.central.client

import dev.central.BrowseDeploymentsRequest
import dev.central.BrowseDeploymentsResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType.Application.Json
import io.ktor.http.contentType
import kotlin.Unit

/**
 * Browse the content of the deployment.
 */
public suspend fun HttpClient.browseDeployments(input: BrowseDeploymentsRequest, builder: suspend HttpRequestBuilder.() -> Unit = {}): BrowseDeploymentsResponse {
  val response = post(urlString = """api/v1/publisher/deployments/files""") {
    contentType(Json)
    setBody(body = input)
    builder()
  }
  val output = response.body<BrowseDeploymentsResponse>()
  return output
}
