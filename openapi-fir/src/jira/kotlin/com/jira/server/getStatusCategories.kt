package com.jira.server

import com.jira.StatusCategoryJsonBean
import io.ktor.http.ContentType.Application.Json
import io.ktor.http.HttpStatusCode.Companion.OK
import io.ktor.server.application.ApplicationCall
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.`get`
import io.ktor.server.routing.accept
import io.ktor.server.routing.route

/**
 * Get all status categories
 * Returns a list of all status categories
 */
public fun Route.getStatusCategories(action: suspend ApplicationCall.() -> StatusCategoryJsonBean) {
  route(path = """/api/2/statuscategory""") {
    accept(Json) {
      `get` {
        val response = call.action()
        call.response.status(OK)
        call.respond(response)
      }
    }
  }
}
