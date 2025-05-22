package com.jira.server

import com.jira.RemoteEntityLinksJsonBean
import io.ktor.http.ContentType.Application.Json
import io.ktor.http.HttpStatusCode.Companion.OK
import io.ktor.server.application.ApplicationCall
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.`get`
import io.ktor.server.routing.accept
import io.ktor.server.routing.route

/**
 * Get remote version links by global ID
 * Returns the remote version links for a given global ID.
 */
public fun Route.getRemoteVersionLinks(action: suspend ApplicationCall.() -> RemoteEntityLinksJsonBean) {
  route(path = """/api/2/version/remotelink""") {
    accept(Json) {
      `get` {
        val response = call.action()
        call.response.status(OK)
        call.respond(response)
      }
    }
  }
}
