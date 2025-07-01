package com.jira.server

import io.ktor.http.HttpStatusCode.Companion.NoContent
import io.ktor.server.application.ApplicationCall
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.delete
import io.ktor.server.routing.route
import kotlin.Unit

/**
 * Remove given project association for specified scheme
 * For the specified issue type scheme, removes the given project association
 */
public fun Route.removeProjectAssociation(action: suspend ApplicationCall.() -> Unit) {
  route(path = """/api/2/issuetypescheme/{schemeId}/associations/{projIdOrKey}""") {
    delete {
      call.action()
      call.respond(NoContent)
    }
  }
}
