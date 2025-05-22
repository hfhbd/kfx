package com.jira.server

import com.jira.EpicBean
import com.jira.EpicUpdateBean
import io.ktor.http.ContentType.Application.Json
import io.ktor.http.HttpStatusCode.Companion.OK
import io.ktor.server.application.ApplicationCall
import io.ktor.server.request.receive
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.accept
import io.ktor.server.routing.contentType
import io.ktor.server.routing.post
import io.ktor.server.routing.route

/**
 * Update an epic's details
 * Performs a partial update of the epic. A partial update means that fields not present in the request JSON will not be updated. Valid values for color are color_1 to color_9.
 */
public fun Route.partiallyUpdateEpic(action: suspend ApplicationCall.(EpicUpdateBean) -> EpicBean) {
  route(path = """/agile/1.0/epic/{epicIdOrKey}""") {
    contentType(Json) {
      accept(Json) {
        post {
          val body = call.receive<EpicUpdateBean>()
          val response = call.action(body)
          call.response.status(OK)
          call.respond(response)
        }
      }
    }
  }
}
