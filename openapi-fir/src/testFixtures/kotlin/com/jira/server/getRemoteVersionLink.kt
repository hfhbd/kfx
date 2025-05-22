package com.jira.server

import com.jira.RemoteEntityLinkJsonBean
import io.ktor.http.ContentType.Application.Json
import io.ktor.http.HttpStatusCode.Companion.OK
import io.ktor.server.application.ApplicationCall
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.`get`
import io.ktor.server.routing.accept
import io.ktor.server.routing.route

/**
 * Get specific remote version link
 * Returns the remote version link associated with the given version ID and global ID.
 */
public fun Route.getRemoteVersionLink(action: suspend ApplicationCall.() -> RemoteEntityLinkJsonBean) {
  route(path = """/api/2/version/{versionId}/remotelink/{globalId}""") {
    accept(Json) {
      `get` {
        val response = call.action()
        call.response.status(OK)
        call.respond(response)
      }
    }
  }
}
