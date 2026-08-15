package client

import APIError
import Saml
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.put
import io.ktor.client.request.setBody
import io.ktor.http.ContentType.Application.Json
import io.ktor.http.contentType
import io.ktor.http.isSuccess
import kotlin.String
import kotlin.Throws
import kotlin.Unit

@Throws(APIError::class)
public suspend fun HttpClient.utilSamlmetadata(input: String, builder: suspend HttpRequestBuilder.() -> Unit = {}): Saml {
  val response = put(urlString = """api/v1/saml-metadata-converter""") {
    contentType(Json)
    setBody(input)
    builder()
  }
  when {
    response.status.isSuccess() -> {
      val output = response.body<Saml>()
      return output
    }

    else -> {
      val output = response.body<APIError>()
      throw output
    }
  }
}
