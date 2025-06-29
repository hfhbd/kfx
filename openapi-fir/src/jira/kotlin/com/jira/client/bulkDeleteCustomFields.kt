package com.jira.client

import com.jira.BulkDeleteResponseBean
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.delete
import io.ktor.client.request.parameter
import kotlin.String
import kotlin.Unit

/**
 * Delete custom fields in bulk
 * Deletes custom fields in bulk.
 *
 * @param ids A list of custom field IDs to delete.
 */
public suspend fun HttpClient.bulkDeleteCustomFields(ids: String? = null, builder: suspend HttpRequestBuilder.() -> Unit = {}): BulkDeleteResponseBean {
  val response = delete(urlString = """api/2/customFields""") {
    parameter("ids", ids)
    builder()
  }
  val output = response.body<BulkDeleteResponseBean>()
  return output
}
