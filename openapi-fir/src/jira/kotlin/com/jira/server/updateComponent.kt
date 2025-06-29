package com.jira.server

import com.jira.ComponentBean
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
 * Update a component
 * Modify a component via PUT. Any fields present in the PUT will override existing values. As a convenience, if a field is not present, it is silently ignored.
 */
public fun Route.updateComponent(action: suspend ApplicationCall.(ComponentBean) -> ComponentBean) {
  route(path = """/api/2/component/{id}""") {
    contentType(Json) {
      accept(Json) {
        put {
          val body = call.receive<ComponentBean>()
          val response = call.action(body)
          call.response.status(OK)
          call.respond(response)
        }
      }
    }
  }
}
