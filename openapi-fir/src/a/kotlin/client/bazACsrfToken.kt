package client

import com.example.Fault
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.`header`
import io.ktor.client.request.head
import io.ktor.http.HttpStatusCode
import responses.BazACsrfToken
import kotlin.String
import kotlin.Unit

/**
 * Get the CSRF Token for BazA
 */
public suspend fun HttpClient.bazACsrfToken(X_CSRF_Token: String = "FETCH", builder: suspend HttpRequestBuilder.() -> Unit = {}): BazACsrfToken {
  val response = head(urlString = """http/foo/bar/baz""") {
    `header`("X-CSRF-Token", X_CSRF_Token)
    builder()
  }
  return when(val status = response.status) {
      HttpStatusCode.OK -> {
          BazACsrfToken.Success(X_CSRF_Token = response.headers["X-CSRF-Token"]!!)
      }
      else -> {
          return BazACsrfToken.Error(body = response.body<Fault>())
      }
  }
}
