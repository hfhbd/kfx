package dev.central.client

import dev.central.PublishingTypePublishingType
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.parameter
import io.ktor.client.request.post
import io.ktor.http.ContentType.MultiPart.FormData
import io.ktor.http.contentType
import kotlin.String
import kotlin.Unit

/**
 * Upload a deployment bundle intended to be published to Maven Central.
 *
 * @param name Deployment/bundle name, optional (will use attached file name if not present).
 * @param publishingType Whether to have the deployment stop in the `VALIDATED` state and require a user to log in and manually approve its progression, or to automatically go directly to `PUBLISHING` when validation has passed.
 */
public suspend fun HttpClient.uploadComponents(
  name: String? = null,
  publishingType: PublishingTypePublishingType? = null,
  builder: suspend HttpRequestBuilder.() -> Unit = {},
): String {
  val response = post(urlString = """api/v1/publisher/upload""") {
    parameter("name", name)
    parameter("publishingType", publishingType)
    contentType(FormData)
    builder()
  }
  val output = response.body<String>()
  return output
}
