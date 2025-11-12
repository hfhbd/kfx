package client

import com.example.Fault
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.`header`
import io.ktor.client.request.head
import io.ktor.http.HttpStatusCode.Companion.OK
import kotlin.String
import kotlin.Unit
import results.BazACsrfTokenResult

/**
 * Get the CSRF Token for BazA
 */
public suspend fun HttpClient.bazACsrfToken(X_CSRF_Token: String = "FETCH", builder: suspend HttpRequestBuilder.() -> Unit = {}): BazACsrfTokenResult {
  val response = head(urlString = """http/foo/bar/baz""") {
    `header`("X-CSRF-Token", X_CSRF_Token)
    builder()
  }
  when (response.status) {
    OK -> {
      return BazACsrfTokenResult.Success(XCSRFToken = response.headers["X-CSRF-Token"]!!)
    }
    else -> {
      val output = response.body<Fault>()
      return BazACsrfTokenResult.Failure(body = output)
    }
  }
}
