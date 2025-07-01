package com.jira.server

import io.ktor.http.ContentType.Application.FormUrlEncoded
import io.ktor.http.HttpStatusCode.Companion.OK
import io.ktor.server.application.ApplicationCall
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.contentType
import io.ktor.server.routing.put
import io.ktor.server.routing.route
import kotlin.Unit

/**
 * Set default system columns for issue navigator using form
 * Sets the default system columns for issue navigator. Admin permission will be required.
 */
public fun Route.setIssueNavigatorDefaultColumnsForm(action: suspend ApplicationCall.() -> Unit) {
  route(path = """/api/2/settings/columns""") {
    contentType(FormUrlEncoded) {
      put {
        call.action()
        call.respond(OK)
      }
    }
  }
}
