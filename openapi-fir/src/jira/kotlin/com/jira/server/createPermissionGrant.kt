package com.jira.server

import com.jira.PermissionGrantBean
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
 * Create a permission grant in a scheme
 * Creates a permission grant in a permission scheme.
 */
public fun Route.createPermissionGrant(action: suspend ApplicationCall.(PermissionGrantBean) -> PermissionGrantBean) {
  route(path = """/api/2/permissionscheme/{schemeId}/permission""") {
    contentType(Json) {
      accept(Json) {
        post {
          val body = call.receive<PermissionGrantBean>()
          val response = call.action(body)
          call.response.status(Created)
          call.respond(response)
        }
      }
    }
  }
}
