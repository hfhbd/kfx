package com.example.foo.client

import com.example.bar.Bar
import com.example.foo.Fault
import com.example.foo.Foo
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType.Text.Xml
import io.ktor.http.contentType
import io.ktor.http.isSuccess
import kotlin.Throws
import kotlin.Unit

/**
 * Create Foo
 */
@Throws(Fault::class)
public suspend fun HttpClient.createFoo(input: Foo, builder: suspend HttpRequestBuilder.() -> Unit = {}): Bar {
  val response = post {
    contentType(Xml)
    setBody(body = input)
    builder()
  }
  if (response.status.isSuccess()) {
    val output = response.body<Bar>()
    return output
  } else {
    val output = response.body<Fault>()
    throw output
  }
}
