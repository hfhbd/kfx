package com.jira.server

import io.ktor.http.HttpStatusCode.Companion.NoContent
import io.ktor.server.application.ApplicationCall
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.post
import io.ktor.server.routing.route
import kotlin.Unit

/**
 * Add field to default screen
 * Moves field on the given tab.
 */
public fun Route.addFieldToDefaultScreen(action: suspend ApplicationCall.() -> Unit) {
  route(path = """/api/2/screens/addToDefault/{fieldId}""") {
    post {
      call.action()
      call.respond(NoContent)
    }
  }
}
