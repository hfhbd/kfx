package com.jira.server

import io.ktor.http.HttpStatusCode.Companion.OK
import io.ktor.server.application.ApplicationCall
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.post
import io.ktor.server.routing.route
import kotlin.Unit

/**
 * Add vote to issue
 * Adds voter (currently logged user) to particular ticket. You need to be logged in to use this method.
 */
public fun Route.addVote(action: suspend ApplicationCall.() -> Unit) {
  route(path = """/api/2/issue/{issueIdOrKey}/votes""") {
    post {
      call.action()
      call.respond(OK)
    }
  }
}
