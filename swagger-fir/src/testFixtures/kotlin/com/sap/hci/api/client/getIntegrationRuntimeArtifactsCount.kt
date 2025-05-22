package com.sap.hci.api.client

import io.ktor.client.HttpClient
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.`get`
import io.ktor.client.request.parameter
import kotlin.String
import kotlin.Unit

/**
 * You can use the following request to get the number of all deployed integration artifacts.
 *
 * @param filter Filter the returned count by property values (not possible for DeployedOn). No nesting possible. Only logical operator ```or``` is allowed. <br>
 * Example1: ```Status eq 'ERROR'``` returns all deployed integration artifacts in status 'ERROR'.<br>
 * Example2: ```Status eq 'ERROR' or Version eq '1.0.0'```
 */
public suspend fun HttpClient.getIntegrationRuntimeArtifactsCount(filter: String? = null, builder: suspend HttpRequestBuilder.() -> Unit = {}) {
  val response = `get`(urlString = """IntegrationRuntimeArtifacts/${'$'}count""") {
    parameter("filter", filter)
    builder()
  }
}
