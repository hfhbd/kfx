package com.jira.server

import com.jira.AssociateProjectsBean
import io.ktor.http.ContentType.Application.Json
import io.ktor.http.HttpStatusCode.Companion.OK
import io.ktor.server.application.ApplicationCall
import io.ktor.server.request.receive
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.contentType
import io.ktor.server.routing.post
import io.ktor.server.routing.route
import kotlin.Unit

/**
 * Add project associations to scheme
 * Adds additional projects to those already associated with the specified issue type scheme
 */
public fun Route.addProjectAssociationsToScheme(action: suspend ApplicationCall.(AssociateProjectsBean) -> Unit) {
  route(path = """/api/2/issuetypescheme/{schemeId}/associations""") {
    contentType(Json) {
      post {
        val body = call.receive<AssociateProjectsBean>()
        call.action(body)
        call.respond(OK)
      }
    }
  }
}
