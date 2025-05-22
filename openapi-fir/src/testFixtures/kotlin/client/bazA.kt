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
import io.ktor.http.contentType
import io.ktor.http.isSuccess
import kotlin.String
import kotlin.Throws
import kotlin.Unit

/**
 * Foo Bar API
 * @param X_CSRF_Token The CSRF Token fetched by executing BazA_CsrfToken first.
 * @param B some Header
 */
@Throws(Fault::class)
public suspend fun HttpClient.bazA(
  input: FooInput,
  X_CSRF_Token: String,
  B: String? = null,
  builder: suspend HttpRequestBuilder.() -> Unit = {},
): String {
  val response = post(urlString = """http/foo/bar/baz""") {
    `header`("X-CSRF-Token", X_CSRF_Token)
    `header`("B", B)
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
