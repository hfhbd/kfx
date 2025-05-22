package com.jira.server

import com.jira.FilterPermissionBean
import com.jira.SharePermissionInputBean
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
 * Add share permissions to filter
 * Adds a share permissions to the given filter. Adding a global permission removes all previous permissions from the filter
 */
public fun Route.addSharePermission(action: suspend ApplicationCall.(SharePermissionInputBean) -> FilterPermissionBean) {
  route(path = """/api/2/filter/{id}/permission""") {
    contentType(Json) {
      accept(Json) {
        post {
          val body = call.receive<SharePermissionInputBean>()
          val response = call.action(body)
          call.response.status(Created)
          call.respond(response)
        }
      }
    }
  }
}
