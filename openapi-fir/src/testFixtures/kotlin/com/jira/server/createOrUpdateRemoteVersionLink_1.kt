package com.jira.server

import com.jira.RemoteEntityLinkJsonBean
import io.ktor.http.ContentType.Application.Json
import io.ktor.http.HttpStatusCode.Companion.Created
import io.ktor.server.application.ApplicationCall
import io.ktor.server.request.receive
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.contentType
import io.ktor.server.routing.post
import io.ktor.server.routing.route
import kotlin.Unit

/**
 * Create or update remote version link with global ID
 * Create a remote version link via POST using the provided global ID.
 */
public fun Route.createOrUpdateRemoteVersionLink_1(action: suspend ApplicationCall.(RemoteEntityLinkJsonBean) -> Unit) {
  route(path = """/api/2/version/{versionId}/remotelink/{globalId}""") {
    contentType(Json) {
      post {
        val body = call.receive<RemoteEntityLinkJsonBean>()
        call.action(body)
        call.respond(Created)
      }
    }
  }
}
