package client

import Catalog
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.`get`
import kotlin.Unit

public suspend fun HttpClient.catalogGet(builder: suspend HttpRequestBuilder.() -> Unit = {}): Catalog {
  val response = `get`(urlString = """v2/catalog""") {
    builder()
  }
  val output = response.body<Catalog>()
  return output
}
