package com.sap.hci.api.client

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.`header`
import io.ktor.client.request.parameter
import io.ktor.client.request.post
import io.ktor.http.isSuccess
import kotlin.String
import kotlin.Throws
import kotlin.Unit
import odata.Error

/**
 * You can use the following request to update a default value mapping in the value mapping artifact.<br>
 *
 * In API sandbox, only read APIs can be tested. You need to configure an API endpoint for your account, where you have the required write permissions to create value mappings.
 *
 * @param X_CSRF_Token CSRF token that is valid for the current session (see resource 'CSRF Token Handling')
 * @param id Id of value mappping artifact.<br>
 * Example: ```'ValueMapping1'```.<br>
 * @param version Version of the value mapping artifact.<br>
 * Example: ```'active'```.<br>
 * @param srcAgency Source Agency.<br>
 * Example: ```'SourceAgency'```.<br>
 * @param srcId Source Id.<br>
 * Example: ```'123'```.<br>
 * @param tgtAgency Target Agency.<br>
 * Example: ```'TargetAgency'```.<br>
 * @param tgtId Target Id.<br>
 * Example: ```'456'```.<br>
 * @param isConfigured Boolean value: true or false
 * @param valMapId Value Mapping Id
 */
@Throws(Error::class)
public suspend fun HttpClient.createUpdateDefaultValMap(
  X_CSRF_Token: String,
  id: String? = null,
  version: String? = null,
  srcAgency: String? = null,
  srcId: String? = null,
  tgtAgency: String? = null,
  tgtId: String? = null,
  isConfigured: String? = null,
  valMapId: String? = null,
  builder: suspend HttpRequestBuilder.() -> Unit = {},
) {
  val response = post(urlString = """UpdateDefaultValMap""") {
    `header`("X-CSRF-Token", X_CSRF_Token)
    parameter("id", id)
    parameter("version", version)
    parameter("srcAgency", srcAgency)
    parameter("srcId", srcId)
    parameter("tgtAgency", tgtAgency)
    parameter("tgtId", tgtId)
    parameter("isConfigured", isConfigured)
    parameter("valMapId", valMapId)
    builder()
  }
  if (response.status.isSuccess()) {
  } else {
    throw response.body<Error>()
  }
}
