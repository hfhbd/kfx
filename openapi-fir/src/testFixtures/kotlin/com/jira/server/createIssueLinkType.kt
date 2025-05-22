package com.jira.server

import com.jira.IssueLinkTypeJsonBean
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
 * Create a new issue link type
 * Create a new issue link type.
 */
public fun Route.createIssueLinkType(action: suspend ApplicationCall.(IssueLinkTypeJsonBean) -> Unit) {
  route(path = """/api/2/issueLinkType""") {
    contentType(Json) {
      post {
        val body = call.receive<IssueLinkTypeJsonBean>()
        call.action(body)
        call.respond(Created)
      }
    }
  }
}
