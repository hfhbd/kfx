package com.jira.server

import com.jira.A11yPersonalSettingBean
import io.ktor.http.ContentType.Application.Json
import io.ktor.http.HttpStatusCode.Companion.OK
import io.ktor.server.application.ApplicationCall
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.`get`
import io.ktor.server.routing.accept
import io.ktor.server.routing.route

/**
 * Get available accessibility personal settings
 * Returns available accessibility personal settings along with `enabled` property that indicates the currently logged-in user preference.
 */
public fun Route.getA11yPersonalSettings(action: suspend ApplicationCall.() -> A11yPersonalSettingBean) {
  route(path = """/api/2/user/a11y/personal-settings""") {
    accept(Json) {
      `get` {
        val response = call.action()
        call.response.status(OK)
        call.respond(response)
      }
    }
  }
}
