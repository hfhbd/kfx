package com.jira.server

import com.jira.VersionBean
import io.ktor.http.ContentType.Application.Json
import io.ktor.http.HttpStatusCode.Companion.OK
import io.ktor.server.application.ApplicationCall
import io.ktor.server.request.receive
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.contentType
import io.ktor.server.routing.put
import io.ktor.server.routing.route
import kotlin.Unit

/**
 * Update version details
 * Updates a version.
 */
public fun Route.updateVersion(action: suspend ApplicationCall.(VersionBean) -> Unit) {
  route(path = """/api/2/version/{id}""") {
    contentType(Json) {
      put {
        val body = call.receive<VersionBean>()
        call.action(body)
        call.respond(OK)
      }
    }
  }
}
