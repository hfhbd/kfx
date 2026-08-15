package com.jira.client

import com.jira.EntityPropertyBean
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.`get`
import io.ktor.http.HttpStatusCode.Companion.NotFound
import kotlin.String
import kotlin.Unit

/**
 * Get value of property from project
 * Returns the value of the property with a given key from the project identified by the key or by the id.
 */
public suspend fun HttpClient.getProperty5(
  propertyKey: String,
  projectIdOrKey: String,
  builder: suspend HttpRequestBuilder.() -> Unit = {},
): EntityPropertyBean? {
  val response = `get`(urlString = """api/2/project/${projectIdOrKey}/properties/${propertyKey}""") {
    builder()
  }
  when {
    response.status == NotFound -> {
      return null
    }
  }
  val output = response.body<EntityPropertyBean>()
  return output
}
