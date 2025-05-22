package com.sap.hci.api.client

import com.sap.hci.api.CreateIntegrationPackagesOverwrite
import com.sap.hci.api.IntegrationPackage
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
 * You can use the following request to create/import an integration package.<br>
 *
 * In API sandbox, only read APIs can be tested. You need to configure an API endpoint for your account, where you have the required write permissions to create integration package.
 *
 * @param X_CSRF_Token CSRF token that is valid for the current session (see resource 'CSRF Token Handling')
 * @param overwrite You can use the Overwrite parameter to overwrite an existing package.
 */
@Throws(Error::class)
public suspend fun HttpClient.createIntegrationPackages(
  X_CSRF_Token: String,
  overwrite: CreateIntegrationPackagesOverwrite? = null,
  builder: suspend HttpRequestBuilder.() -> Unit = {},
): IntegrationPackage {
  val response = post(urlString = """IntegrationPackages""") {
    `header`("X-CSRF-Token", X_CSRF_Token)
    parameter("overwrite", overwrite)
    builder()
  }
  if (response.status.isSuccess()) {
    return response.body<IntegrationPackage>()
  } else {
    throw response.body<Error>()
  }
}
