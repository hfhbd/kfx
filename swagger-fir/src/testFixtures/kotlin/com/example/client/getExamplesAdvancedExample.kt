package com.example.client

import com.example.InputWithProcessorConfig
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.`get`
import kotlin.Unit

/**
 * Returns an advanced example including an Input object and processor configuration
 */
public suspend fun HttpClient.getExamplesAdvancedExample(builder: suspend HttpRequestBuilder.() -> Unit = {}): InputWithProcessorConfig {
  val response = `get`(urlString = """examples/advancedExample""") {
    builder()
  }
  val output = response.body<InputWithProcessorConfig>()
  return output
}
