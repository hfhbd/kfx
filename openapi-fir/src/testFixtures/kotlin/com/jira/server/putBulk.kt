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
 * Update application roles
 * Updates the ApplicationRoles with the passed data if the version hash is the same as the server. Only the groups and default groups setting of the role may be updated. Requests to change the key or the name of the role will be silently ignored. It is acceptable to pass only the roles that are updated as roles that are present in the server but not in data to update with, will not be deleted.
 */
public fun Route.putBulk(action: suspend ApplicationCall.(ApplicationRoleBean) -> ApplicationRoleBean) {
  route(path = """/api/2/applicationrole""") {
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
