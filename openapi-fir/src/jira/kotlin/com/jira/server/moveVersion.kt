package com.jira.server

import com.jira.VersionBean
import com.jira.VersionMoveBean
import io.ktor.http.ContentType.Application.Json
import io.ktor.http.HttpStatusCode.Companion.OK
import io.ktor.server.application.ApplicationCall
import io.ktor.server.request.receive
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.accept
import io.ktor.server.routing.contentType
import io.ktor.server.routing.post
import io.ktor.server.routing.route

/**
 * Modify version's sequence
 * Modify a version's sequence within a project.
 * The move version bean has 2 alternative field value pairs:
 * - position: An absolute position, which may have a value of 'First', 'Last', 'Earlier' or 'Later'
 * - after: A version to place this version after.  The value should be the self link of another version
 */
public fun Route.moveVersion(action: suspend ApplicationCall.(VersionMoveBean) -> VersionBean) {
  route(path = """/api/2/version/{id}/move""") {
    contentType(Json) {
      accept(Json) {
        post {
          val body = call.receive<VersionMoveBean>()
          val response = call.action(body)
          call.response.status(OK)
          call.respond(response)
        }
      }
    }
  }
}
