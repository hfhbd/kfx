package com.jira.server

import com.jira.BulkDeleteResponseBean
import io.ktor.http.ContentType.Application.Json
import io.ktor.http.HttpStatusCode.Companion.OK
import io.ktor.server.application.ApplicationCall
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.accept
import io.ktor.server.routing.delete
import io.ktor.server.routing.route

/**
 * Delete custom fields in bulk
 * Deletes custom fields in bulk.
 */
public fun Route.bulkDeleteCustomFields(action: suspend ApplicationCall.() -> BulkDeleteResponseBean) {
  route(path = """/api/2/customFields""") {
    accept(Json) {
      delete {
        val response = call.action()
        call.response.status(OK)
        call.respond(response)
      }
    }
  }
}
