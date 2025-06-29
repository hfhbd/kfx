package com.jira.server

import com.jira.IssueLinkTypeJsonBean
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
 * Update the specified issue link type
 * Update the specified issue link type.
 */
public fun Route.updateIssueLinkType(action: suspend ApplicationCall.(IssueLinkTypeJsonBean) -> Unit) {
  route(path = """/api/2/issueLinkType/{issueLinkTypeId}""") {
    contentType(Json) {
      put {
        val body = call.receive<IssueLinkTypeJsonBean>()
        call.action(body)
        call.respond(OK)
      }
    }
  }
}
