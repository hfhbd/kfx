package client

import dev.example.Fault
import dev.example.FooSealed
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType.Application.Json
import io.ktor.http.contentType
import io.ktor.http.isSuccess
import kotlin.String
import kotlin.Throws
import kotlin.Unit

/**
 * Foo Bar API
 */
@Throws(Fault::class)
public suspend fun HttpClient.createSealed(input: FooSealed, builder: suspend HttpRequestBuilder.() -> Unit = {}): String {
  val response = post(urlString = """sealed""") {
    contentType(Json)
    setBody(body = input)
    builder()
  }
  if (response.status.isSuccess()) {
    val output = response.body<String>()
    return output
  } else {
    val output = response.body<Fault>()
    throw output
  }
}
