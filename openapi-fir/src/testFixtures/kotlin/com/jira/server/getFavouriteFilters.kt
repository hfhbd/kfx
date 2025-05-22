package com.jira.server

import com.jira.FilterBean
import io.ktor.http.ContentType.Application.Json
import io.ktor.http.HttpStatusCode.Companion.OK
import io.ktor.server.application.ApplicationCall
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.`get`
import io.ktor.server.routing.accept
import io.ktor.server.routing.route

/**
 * Get favourite filters
 * Returns the favourite filters of the logged-in user
 */
public fun Route.getFavouriteFilters(action: suspend ApplicationCall.() -> FilterBean) {
  route(path = """/api/2/filter/favourite""") {
    accept(Json) {
      `get` {
        val response = call.action()
        call.response.status(OK)
        call.respond(response)
      }
    }
  }
}
