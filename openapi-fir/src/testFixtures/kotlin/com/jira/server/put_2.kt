package com.jira.server

import com.jira.ApplicationRoleBean
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
 * Update application role
 * Updates the ApplicationRole with the passed data. Only the groups and default groups setting of the role may be updated. Requests to change the key or the name of the role will be silently ignored.
 */
public fun Route.put_2(action: suspend ApplicationCall.(ApplicationRoleBean) -> ApplicationRoleBean) {
  route(path = """/api/2/applicationrole/{key}""") {
    contentType(Json) {
      accept(Json) {
        put {
          val body = call.receive<ApplicationRoleBean>()
          val response = call.action(body)
          call.response.status(OK)
          call.respond(response)
        }
      }
    }
  }
}
