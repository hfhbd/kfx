package com.jira.server

import com.jira.PermissionSchemeBean
import io.ktor.http.ContentType.Application.Json
import io.ktor.http.HttpStatusCode.Companion.Created
import io.ktor.server.application.ApplicationCall
import io.ktor.server.request.receive
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.accept
import io.ktor.server.routing.contentType
import io.ktor.server.routing.post
import io.ktor.server.routing.route

/**
 * Create a new permission scheme
 * Create a new permission scheme. This method can create schemes with a defined permission set, or without.
 */
public fun Route.createPermissionScheme(action: suspend ApplicationCall.(PermissionSchemeBean) -> PermissionSchemeBean) {
  route(path = """/api/2/permissionscheme""") {
    contentType(Json) {
      accept(Json) {
        post {
          val body = call.receive<PermissionSchemeBean>()
          val response = call.action(body)
          call.response.status(Created)
          call.respond(response)
        }
      }
    }
  }
}
