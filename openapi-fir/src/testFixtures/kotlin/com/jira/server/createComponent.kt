package com.jira.server

import com.jira.ComponentBean
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
 * Create component
 * Create a component via POST.
 */
public fun Route.createComponent(action: suspend ApplicationCall.(ComponentBean) -> ComponentBean) {
  route(path = """/api/2/component""") {
    contentType(Json) {
      accept(Json) {
        post {
          val body = call.receive<ComponentBean>()
          val response = call.action(body)
          call.response.status(Created)
          call.respond(response)
        }
      }
    }
  }
}
