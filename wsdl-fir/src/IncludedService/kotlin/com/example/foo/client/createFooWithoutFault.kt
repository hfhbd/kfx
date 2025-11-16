package com.example.foo.client

import com.example.bar.Bar
import com.example.foo.Foo
import com.example.foo.results.CreateFooWithoutFaultResult
import io.github.hfhbd.kfx.soap11.Envelope
import io.github.hfhbd.kfx.soap11.Fault
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.`header`
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType.Text.Xml
import io.ktor.http.HttpStatusCode.Companion.OK
import io.ktor.http.contentType
import kotlin.Unit

/**
 * Create Foo
 */
public suspend fun HttpClient.createFooWithoutFault(input: Foo, builder: suspend HttpRequestBuilder.() -> Unit = {}): CreateFooWithoutFaultResult {
  val response = post {
    `header`("SOAPAction", "http://example.com/FooService/createFooWithoutFault")
    contentType(Xml)
    setBody(
        Envelope<Foo>(
          header = null,
          body = input,
        ),
        )
    builder()
  }
  when (response.status) {
    OK -> {
      val output = response.body<Envelope<Bar>>()
      return CreateFooWithoutFaultResult.Success(body = output)
    }
    else -> {
      val output = response.body<Envelope<Fault>>()
      return CreateFooWithoutFaultResult.Failure(body = output)
    }
  }
}
