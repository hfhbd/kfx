package com.jira.server

import com.jira.FilterBean
import io.ktor.http.ContentType.Application.Json
import io.ktor.http.HttpStatusCode.Companion.OK
import io.ktor.server.application.ApplicationCall
import io.ktor.server.request.receive
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.accept
import io.ktor.server.routing.contentType
import io.ktor.server.routing.put
import io.ktor.server.routing.route

/**
 * Update an existing filter
 * Updates an existing filter, and returns its new value. The following properties of a filter can be updated: 'jql', 'name', 'description'. Additionally, administrators can also update the 'owner' field. To get, set or unset 'favourite', use rest/api/1.0/filters/{id}/favourite with GET, PUT and DELETE methods instead.
 */
public fun Route.editFilter(action: suspend ApplicationCall.(FilterBean) -> FilterBean) {
  route(path = """/api/2/filter/{id}""") {
    contentType(Json) {
      accept(Json) {
        put {
          val body = call.receive<FilterBean>()
          val response = call.action(body)
          call.response.status(OK)
          call.respond(response)
        }
      }
    }
  }
}
