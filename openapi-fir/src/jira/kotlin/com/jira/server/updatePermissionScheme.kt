package com.jira.server

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
 * Update a permission scheme
 * Updates a permission scheme. If the permissions list is present then it will be set in the permission scheme, which basically means it will overwrite any permission grants that existed in the permission scheme. Sending an empty list will remove all permission grants from the permission scheme. To update just the name and description, do not send permissions list at all. To add or remove a single permission grant instead of updating the whole list at once use the {schemeId}/permission/ resource.
 */
public fun Route.updatePermissionScheme(action: suspend ApplicationCall.(PermissionSchemeBean) -> PermissionSchemeBean) {
  route(path = """/api/2/permissionscheme/{schemeId}""") {
    contentType(Json) {
      accept(Json) {
        put {
          val body = call.receive<PermissionSchemeBean>()
          val response = call.action(body)
          call.response.status(OK)
          call.respond(response)
        }
      }
    }
  }
}
