package com.jira.server

import com.jira.FilterBean
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
 * Create a new filter
 * Creates a new filter, and returns newly created filter. Currently sets permissions just using the users default sharing permissions
 */
public fun Route.createFilter(action: suspend ApplicationCall.(FilterBean) -> FilterBean) {
  route(path = """/api/2/filter""") {
    contentType(Json) {
      accept(Json) {
        post {
          val body = call.receive<FilterBean>()
          val response = call.action(body)
          call.response.status(OK)
          call.respond(response)
        }
      }
    }
  }
}
