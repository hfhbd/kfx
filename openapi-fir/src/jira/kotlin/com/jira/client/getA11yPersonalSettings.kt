package com.jira.client

import com.jira.A11yPersonalSettingBean
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.`get`
import kotlin.Unit

/**
 * Get available accessibility personal settings
 * Returns available accessibility personal settings along with `enabled` property that indicates the currently logged-in user preference.
 */
public suspend fun HttpClient.getA11yPersonalSettings(builder: suspend HttpRequestBuilder.() -> Unit = {}): A11yPersonalSettingBean {
  val response = `get`(urlString = """api/2/user/a11y/personal-settings""") {
    builder()
  }
  val output = response.body<A11yPersonalSettingBean>()
  return output
}
