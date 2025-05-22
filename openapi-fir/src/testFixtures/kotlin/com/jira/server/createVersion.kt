package com.jira.server

import com.jira.VersionBean
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
 * Create new version
 * Creates a version.
 */
public fun Route.createVersion(action: suspend ApplicationCall.(VersionBean) -> VersionBean) {
  route(path = """/api/2/version""") {
    contentType(Json) {
      accept(Json) {
        post {
          val body = call.receive<VersionBean>()
          val response = call.action(body)
          call.response.status(Created)
          call.respond(response)
        }
      }
    }
  }
}
