package com.jira.server

import com.jira.PrioritySchemeBean
import com.jira.PrioritySchemeUpdateBean
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
 * Create new priority scheme
 * Creates new priority scheme.
 */
public fun Route.createPriorityScheme(action: suspend ApplicationCall.(PrioritySchemeUpdateBean) -> PrioritySchemeBean) {
  route(path = """/api/2/priorityschemes""") {
    contentType(Json) {
      accept(Json) {
        post {
          val body = call.receive<PrioritySchemeUpdateBean>()
          val response = call.action(body)
          call.response.status(Created)
          call.respond(response)
        }
      }
    }
  }
}
