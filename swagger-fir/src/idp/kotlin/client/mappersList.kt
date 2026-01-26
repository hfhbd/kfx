package client

import MappersList
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.`get`
import io.ktor.http.ContentType.Application.Json
import io.ktor.http.contentType
import kotlin.String
import kotlin.Unit
import kotlin.collections.List

/**
 * @param protocol Protocol for which to retrieve the predefined mappers
 */
public suspend fun HttpClient.mappersList(protocol: String, builder: suspend HttpRequestBuilder.() -> Unit = {}): List<MappersList> {
  val response = `get`(urlString = """api/v1/predefined-mappers/${protocol}""") {
    contentType(Json)
    builder()
  }
  val output = response.body<List<MappersList>>()
  return output
}
