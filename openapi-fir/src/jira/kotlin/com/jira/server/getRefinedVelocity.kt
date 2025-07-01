package com.jira.server

import com.jira.BooleanSettingBean
import io.ktor.http.ContentType.Application.Json
import io.ktor.http.HttpStatusCode.Companion.OK
import io.ktor.server.application.ApplicationCall
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.`get`
import io.ktor.server.routing.accept
import io.ktor.server.routing.route

/**
 * Get the value of the refined velocity setting
 * Returns the value of the setting for refined velocity chart
 */
public fun Route.getRefinedVelocity(action: suspend ApplicationCall.() -> BooleanSettingBean) {
  route(path = """/agile/1.0/board/{boardId}/settings/refined-velocity""") {
    accept(Json) {
      `get` {
        val response = call.action()
        call.response.status(OK)
        call.respond(response)
      }
    }
  }
}
