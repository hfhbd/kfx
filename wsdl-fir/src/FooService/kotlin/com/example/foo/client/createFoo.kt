package com.example.foo.client

import com.example.bar.Bar
import com.example.foo.Fault
import com.example.foo.Foo
import com.example.foo.results.CreateFooResult
import io.github.hfhbd.kfx.soap11.Envelope
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
public suspend fun HttpClient.createFoo(input: Foo, builder: suspend HttpRequestBuilder.() -> Unit = {}): CreateFooResult {
  val response = post {
    `header`("SOAPAction", "http://example.com/foo/FooServicePortType/CreateFoo")
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
      return CreateFooResult.Success(body = output)
    }
    else -> {
      val output = response.body<Envelope<Fault>>()
      return CreateFooResult.Failure(body = output)
    }
  }
}
