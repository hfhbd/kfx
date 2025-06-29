package com.sap.hci.api.client

import com.sap.hci.api.GetCustomTagConfigurationsByCustomTagsValue
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.`get`
import kotlin.Unit

/**
 * You can use the following request to get all custom tags.
 */
public suspend fun HttpClient.getCustomTagConfigurationsByCustomTagsValue(builder: suspend HttpRequestBuilder.() -> Unit = {}): GetCustomTagConfigurationsByCustomTagsValue {
  val response = `get`(urlString = """CustomTagConfigurations('CustomTags')/${'$'}value""") {
    builder()
  }
  val output = response.body<GetCustomTagConfigurationsByCustomTagsValue>()
  return output
}
