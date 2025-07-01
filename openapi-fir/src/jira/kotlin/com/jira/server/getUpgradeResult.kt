package com.jira.server

import com.jira.UpgradeResultBean
import io.ktor.http.ContentType.Application.Json
import io.ktor.http.HttpStatusCode.Companion.OK
import io.ktor.server.application.ApplicationCall
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.`get`
import io.ktor.server.routing.accept
import io.ktor.server.routing.route

/**
 * Get result of the last upgrade task
 * Returns the result of the last upgrade task.
 */
public fun Route.getUpgradeResult(action: suspend ApplicationCall.() -> UpgradeResultBean) {
  route(path = """/api/2/upgrade""") {
    accept(Json) {
      `get` {
        val response = call.action()
        call.response.status(OK)
        call.respond(response)
      }
    }
  }
}
