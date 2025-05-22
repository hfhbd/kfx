package com.example.client

import com.example.ProcessorConfiguration
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.`get`
import io.ktor.client.request.parameter
import io.ktor.http.ContentType.Application.Json
import io.ktor.http.contentType
import kotlin.String
import kotlin.Unit
import kotlin.collections.List

/**
 * Returns a list of available processor configurations
 *
 * @param connectorType The type of connector that is used
 * @param connectorId The identifier of the connector instance
 * @param connectorVersion The version of the connector that is expected to process this LDIF file
 * @param processingDirection The data flow direction, could be [inbound, outbound]
 * @param processingMode The processing mode, could be [partial, full]
 */
public suspend fun HttpClient.getProcessorConfigurations(
  connectorType: String? = null,
  connectorId: String? = null,
  connectorVersion: String? = null,
  processingDirection: String? = null,
  processingMode: String? = null,
  builder: suspend HttpRequestBuilder.() -> Unit = {},
): List<ProcessorConfiguration> {
  val response = `get`(urlString = """configurations""") {
    parameter("connectorType", connectorType)
    parameter("connectorId", connectorId)
    parameter("connectorVersion", connectorVersion)
    parameter("processingDirection", processingDirection)
    parameter("processingMode", processingMode)
    contentType(Json)
    builder()
  }
  val output = response.body<List<ProcessorConfiguration>>()
  return output
}
