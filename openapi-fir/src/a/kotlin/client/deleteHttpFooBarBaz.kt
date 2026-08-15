package client

import com.example.Fault
import com.example.FooInput
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.`header`
import io.ktor.client.request.delete
import io.ktor.client.request.setBody
import io.ktor.http.ContentType.Application.Json
import io.ktor.http.contentType
import io.ktor.http.isSuccess
import kotlin.String
import kotlin.Unit
import results.DeleteHttpFooBarBazResult

/**
 * Using 2XX and 4XX
 * @param B some Header
 */
public suspend fun HttpClient.deleteHttpFooBarBaz(
  input: FooInput,
  B: String? = null,
  builder: suspend HttpRequestBuilder.() -> Unit = {},
): DeleteHttpFooBarBazResult {
  val response = delete(urlString = """http/foo/bar/baz""") {
    `header`("B", B)
    contentType(Json)
    setBody(input)
    builder()
  }
  when {
    response.status.isSuccess() -> {
      val output = response.body<String>()
      return DeleteHttpFooBarBazResult.Success(body = output, logid = response.headers["logid"])
    }
    else -> {
      val output = response.body<Fault>()
      return DeleteHttpFooBarBazResult.Failure(body = output, logid = response.headers["logid"])
    }
  }
}
