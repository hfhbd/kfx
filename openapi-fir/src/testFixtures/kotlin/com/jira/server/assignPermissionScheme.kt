package com.jira.server

import com.jira.IdBean
import com.jira.PermissionSchemeBean
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
 * Assign permission scheme to project
 * Assigns a permission scheme with a project
 */
public fun Route.assignPermissionScheme(action: suspend ApplicationCall.(IdBean) -> PermissionSchemeBean) {
  route(path = """/api/2/project/{projectKeyOrId}/permissionscheme""") {
    contentType(Json) {
      accept(Json) {
        put {
          val body = call.receive<IdBean>()
          val response = call.action(body)
          call.response.status(OK)
          call.respond(response)
        }
      }
    }
  }
}
