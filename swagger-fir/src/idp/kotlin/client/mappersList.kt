package client

import MappersListItems
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.`get`
import kotlin.String
import kotlin.Unit
import kotlin.collections.List

/**
 * @param protocol Protocol for which to retrieve the predefined mappers
 */
public suspend fun HttpClient.mappersList(protocol: String, builder: suspend HttpRequestBuilder.() -> Unit = {}): List<MappersListItems> {
  val response = `get`(urlString = """api/v1/predefined-mappers/${protocol}""") {
    builder()
  }
  val output = response.body<List<MappersListItems>>()
  return output
}
