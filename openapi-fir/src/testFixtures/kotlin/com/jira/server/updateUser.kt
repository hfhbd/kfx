package com.jira.server

import com.jira.UserWriteBean
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
 * Update currently logged user
 * Modify currently logged user. The 'value' fields present will override the existing value. Fields skipped in request will not be changed. Only email and display name can be change that way. Requires user password.
 */
public fun Route.updateUser(action: suspend ApplicationCall.(UserWriteBean) -> UserWriteBean) {
  route(path = """/api/2/myself""") {
    contentType(Json) {
      accept(Json) {
        put {
          val body = call.receive<UserWriteBean>()
          val response = call.action(body)
          call.response.status(OK)
          call.respond(response)
        }
      }
    }
  }
}
