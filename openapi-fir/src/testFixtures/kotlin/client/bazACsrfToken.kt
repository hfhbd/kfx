package client

import com.example.Fault
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.`header`
import io.ktor.client.request.head
import io.ktor.http.isSuccess
import kotlin.String
import kotlin.Throws
import kotlin.Unit

/**
 * Get the CSRF Token for BazA
 */
@Throws(Fault::class)
public suspend fun HttpClient.bazACsrfToken(X_CSRF_Token: String = "FETCH", builder: suspend HttpRequestBuilder.() -> Unit = {}) {
  val response = head(urlString = """http/foo/bar/baz""") {
    `header`("X-CSRF-Token", X_CSRF_Token)
    builder()
  }
  if (response.status.isSuccess()) {
  } else {
    val output = response.body<Fault>()
    throw output
  }
}
