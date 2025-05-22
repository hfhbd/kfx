package com.jira.server

import com.jira.BooleanSettingBean
import io.ktor.http.ContentType.Application.Json
import io.ktor.http.HttpStatusCode.Companion.OK
import io.ktor.server.application.ApplicationCall
import io.ktor.server.request.receive
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.contentType
import io.ktor.server.routing.put
import io.ktor.server.routing.route
import kotlin.Unit

/**
 * Update the board's refined velocity setting
 * Sets the value of the specified board's refined velocity setting.
 */
public fun Route.setRefinedVelocity(action: suspend ApplicationCall.(BooleanSettingBean) -> Unit) {
  route(path = """/agile/1.0/board/{boardId}/settings/refined-velocity""") {
    contentType(Json) {
      put {
        val body = call.receive<BooleanSettingBean>()
        call.action(body)
        call.respond(OK)
      }
    }
  }
}
