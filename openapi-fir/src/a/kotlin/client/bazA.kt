package client

import com.example.Fault
import com.example.FooInput
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.`header`
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType.Application.Json
import io.ktor.http.HttpStatusCode
import io.ktor.http.contentType
import io.ktor.http.isSuccess
import responses.BazA
import kotlin.String
import kotlin.Unit

/**
 * Foo Bar API
 * @param X_CSRF_Token The CSRF Token fetched by executing BazA_CsrfToken first.
 * @param B some Header
 */
public suspend fun HttpClient.bazA(
  input: FooInput,
  X_CSRF_Token: String,
  B: String? = null,
  builder: suspend HttpRequestBuilder.() -> Unit = {},
): BazA {
  val response = post(urlString = """http/foo/bar/baz""") {
    `header`("X-CSRF-Token", X_CSRF_Token)
    `header`("B", B)
    contentType(Json)
    setBody(body = input)
    builder()
  }
  return when (val status = response.status) {
      HttpStatusCode.OK -> {
          BazA.Success(
            body = response.body<String>(),
            logid = response.headers["logid"]!!,
          )
      }
      else -> {
          BazA.Error(body = response.body<Fault>())
      }
  }
}
