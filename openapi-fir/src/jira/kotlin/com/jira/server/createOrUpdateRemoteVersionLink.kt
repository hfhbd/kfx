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
 * Create or update remote version link without global ID
 * Create a remote version link via POST. The link's global ID will be taken from the JSON payload if provided; otherwise, it will be generated.
 */
public fun Route.createOrUpdateRemoteVersionLink(action: suspend ApplicationCall.(RemoteEntityLinkJsonBean) -> Unit) {
  route(path = """/api/2/version/{versionId}/remotelink""") {
    contentType(Json) {
      post {
        val body = call.receive<RemoteEntityLinkJsonBean>()
        call.action(body)
        call.respond(Created)
      }
    }
  }
}
