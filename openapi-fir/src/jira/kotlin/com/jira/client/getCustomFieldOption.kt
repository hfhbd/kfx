package com.jira.client

import com.jira.CustomFieldOptionBean
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.`get`
import io.ktor.http.HttpStatusCode.Companion.NotFound
import kotlin.String
import kotlin.Unit

/**
 * Get custom field option by ID
 * Returns a full representation of the Custom Field Option that has the given id.
 *
 * @param id a String containing an Custom Field Option id.
 */
public suspend fun HttpClient.getCustomFieldOption(id: String, builder: suspend HttpRequestBuilder.() -> Unit = {}): CustomFieldOptionBean? {
  val response = `get`(urlString = """api/2/customFieldOption/${id}""") {
    builder()
  }
  when {
    response.status == NotFound -> {
      return null
    }
  }
  val output = response.body<CustomFieldOptionBean>()
  return output
}
