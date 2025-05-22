package dev.central.client

import dev.central.BrowseDeployments
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
public suspend fun HttpClient.browseDeployments(input: BrowseDeployments, builder: suspend HttpRequestBuilder.() -> Unit = {}): BrowseDeployments {
  val response = post(urlString = """api/v1/publisher/deployments/files""") {
    contentType(Json)
    setBody(body = input)
    builder()
  }
  val output = response.body<BrowseDeployments>()
  return output
}
