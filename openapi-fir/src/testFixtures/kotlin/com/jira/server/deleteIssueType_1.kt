package com.jira.server

import io.ktor.http.HttpStatusCode.Companion.NoContent
import io.ktor.server.application.ApplicationCall
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.delete
import io.ktor.server.routing.route
import kotlin.Unit

/**
 * Delete specified issue type and migrate associated issues
 * Deletes the specified issue type. If the issue type has any associated issues, these issues will be migrated to the alternative issue type specified in the parameter.
 */
public fun Route.deleteIssueType_1(action: suspend ApplicationCall.() -> Unit) {
  route(path = """/api/2/issuetype/{id}""") {
    delete {
      call.action()
      call.respond(NoContent)
    }
  }
}
