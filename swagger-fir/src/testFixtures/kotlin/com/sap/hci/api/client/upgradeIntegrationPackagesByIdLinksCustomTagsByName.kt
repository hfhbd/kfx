package com.sap.hci.api.client

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.`header`
import io.ktor.client.request.put
import io.ktor.http.isSuccess
import kotlin.String
import kotlin.Throws
import kotlin.Unit
import odata.Error

/**
 * You can use the following request to update a Custom Tag.<br>
 *
 * In API sandbox, only read APIs can be tested. You need to configure an API endpoint for your account, where you have the required  permissions to update Custom Tags.
 *
 * @param id ID of integration package. <br>
 * Example: ```CloudPlatformAPITestPackage```
 * @param name Name of the Custom Tag which values are to be updated.
 * @param X_CSRF_Token CSRF token that is valid for the current session (see resource 'CSRF Token Handling')
 */
@Throws(Error::class)
public suspend fun HttpClient.upgradeIntegrationPackagesByIdLinksCustomTagsByName(
  id: String,
  name: String,
  X_CSRF_Token: String,
  builder: suspend HttpRequestBuilder.() -> Unit = {},
) {
  val response = put(urlString = """IntegrationPackages('${id}')/${'$'}links/CustomTags('${name}')""") {
    `header`("X-CSRF-Token", X_CSRF_Token)
    builder()
  }
  if (response.status.isSuccess()) {
  } else {
    throw response.body<Error>()
  }
}
