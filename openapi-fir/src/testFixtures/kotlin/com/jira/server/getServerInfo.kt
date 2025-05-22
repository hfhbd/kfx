package com.jira.server

import com.jira.ServerInfoBean
import io.ktor.http.ContentType.Application.Json
import io.ktor.http.HttpStatusCode.Companion.OK
import io.ktor.server.application.ApplicationCall
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.`get`
import io.ktor.server.routing.accept
import io.ktor.server.routing.route

/**
 * Get general information about the current Jira server
 * Returns general information about the current Jira server.
 */
public fun Route.getServerInfo(action: suspend ApplicationCall.() -> ServerInfoBean) {
  route(path = """/api/2/serverInfo""") {
    accept(Json) {
      `get` {
        val response = call.action()
        call.response.status(OK)
        call.respond(response)
      }
    }
  }
}
