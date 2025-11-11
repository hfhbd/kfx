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
import io.ktor.http.HttpStatusCode.Companion.OK
import io.ktor.http.contentType
import kotlin.String
import kotlin.Unit
import results.BazAResult

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
): BazAResult {
  val response = post(urlString = """http/foo/bar/baz""") {
    `header`("X-CSRF-Token", X_CSRF_Token)
    `header`("B", B)
    contentType(Json)
    setBody(input)
    builder()
  }
  when (response.status) {
    OK -> {
      val output = response.body<String>()
      return BazAResult.Success(
        body =  output,
        logid = response.headers["logid"]!!,
      )
    }
    else -> {
      return BazAResult.Failure(
        body = response.body<Fault>(),
        logid = response.headers["logid"]!!,
      )
    }
  }
}
