package com.sap.hci.api.client

import io.ktor.client.HttpClient
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.`get`
import io.ktor.http.ContentType.Application.Json
import io.ktor.http.contentType
import kotlin.String
import kotlin.Unit

/**
 * You can use the following request to get error information of a deployed integration artifact by Id.<br>
 *
 * In API sandbox, only the deployed integration flow with Id '__IntegrationFlow_FAILED_DEPLOYMENT__' provides the required error information.
 *
 * @param id Id of deployed integration artifact <br>
 * Example: ```IntegrationFlow_FAILED_DEPLOYMENT```
 */
public suspend fun HttpClient.getIntegrationRuntimeArtifactsByIdErrorInformationValue(id: String, builder: suspend HttpRequestBuilder.() -> Unit = {}) {
  val response = `get`(urlString = """IntegrationRuntimeArtifacts('${id}')/ErrorInformation/${'$'}value""") {
    contentType(Json)
    builder()
  }
}
