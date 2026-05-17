package client

import com.example.Fault
import com.example.FooInput
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.`header`
import io.ktor.client.request.patch
import io.ktor.client.request.setBody
import io.ktor.http.ContentType.Application.Json
import io.ktor.http.HttpStatusCode.Companion.Created
import io.ktor.http.contentType
import kotlin.String
import kotlin.Unit
import results.PatchHttpFooBarBazResult

/**
 * Foo Bar API
 * @param X_CSRF_Token The CSRF Token fetched by executing BazA_CsrfToken first.
 * @param B some Header
 */
public suspend fun HttpClient.patchHttpFooBarBaz(
  input: FooInput,
  X_CSRF_Token: String,
  B: String? = null,
  builder: suspend HttpRequestBuilder.() -> Unit = {},
): PatchHttpFooBarBazResult {
  val response = patch(urlString = """http/foo/bar/baz""") {
    `header`("X-CSRF-Token", X_CSRF_Token)
    `header`("B", B)
    contentType(Json)
    setBody(input)
    builder()
  }
  when (response.status) {
    Created -> {
      val output = response.body<String>()
      return PatchHttpFooBarBazResult.Success(body = output, logid = response.headers["logid"])
    }
    else -> {
      val output = response.body<Fault>()
      return PatchHttpFooBarBazResult.Failure(body = output, logid = response.headers["logid"])
    }
  }
}
