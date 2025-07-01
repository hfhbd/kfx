package com.jira.server

import com.jira.CreateUpdateRoleRequestBean
import com.jira.ProjectRoleBean
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
 * Fully updates a role's name and description
 * Fully updates a roles. Both name and description must be given.
 */
public fun Route.fullyUpdateProjectRole(action: suspend ApplicationCall.(CreateUpdateRoleRequestBean) -> ProjectRoleBean) {
  route(path = """/api/2/role/{id}""") {
    contentType(Json) {
      accept(Json) {
        put {
          val body = call.receive<CreateUpdateRoleRequestBean>()
          val response = call.action(body)
          call.response.status(OK)
          call.respond(response)
        }
      }
    }
  }
}
