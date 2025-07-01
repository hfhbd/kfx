package com.sap.hci.api.client

import io.ktor.client.HttpClient
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.`header`
import io.ktor.client.request.delete
import kotlin.String
import kotlin.Unit

/**
 * You can use the following request to delete an existing integration package.<br>
 *
 * In API sandbox, only read APIs can be tested. You need to configure an API endpoint for your account, where you have the required write permissions to delete integration package.
 *
 * @param id ID of integration package
 * @param X_CSRF_Token CSRF token that is valid for the current session (see resource 'CSRF Token Handling')
 */
public suspend fun HttpClient.deleteIntegrationPackagesById(
  id: String,
  X_CSRF_Token: String,
  builder: suspend HttpRequestBuilder.() -> Unit = {},
) {
  val response = delete(urlString = """IntegrationPackages('${id}')""") {
    `header`("X-CSRF-Token", X_CSRF_Token)
    builder()
  }
}
