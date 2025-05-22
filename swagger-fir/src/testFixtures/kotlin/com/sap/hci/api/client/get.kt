package com.sap.hci.api.client

import io.ktor.client.HttpClient
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.`get`
import io.ktor.client.request.`header`
import kotlin.String
import kotlin.Unit

/**
 * You can use the following request to get the CSRF token for this session, which is required for write access via POST, PUT and DELETE operations. Copy the received X-CSRF-Token from the response header.<br>
 *
 * **In API sandbox this request is not relevant!**
 *
 * @param X_CSRF_Token To retrieve a new CSRF token the value of the header must be set to ```Fetch```.
 */
public suspend fun HttpClient.`get`(X_CSRF_Token: String = "Fetch", builder: suspend HttpRequestBuilder.() -> Unit = {}) {
  val response = `get`(urlString = """""") {
    `header`("X-CSRF-Token", X_CSRF_Token)
    builder()
  }
}
