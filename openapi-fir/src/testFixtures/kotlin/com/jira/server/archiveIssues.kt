package com.jira.server

import io.ktor.http.ContentType.Text.Plain
import io.ktor.http.HttpStatusCode.Companion.OK
import io.ktor.server.application.ApplicationCall
import io.ktor.server.request.receive
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.accept
import io.ktor.server.routing.contentType
import io.ktor.server.routing.post
import io.ktor.server.routing.route
import kotlin.String

/**
 * Archive list of issues
 * Archives a list of issues.
 */
public fun Route.archiveIssues(action: suspend ApplicationCall.(String) -> String) {
  route(path = """/api/2/issue/archive""") {
    contentType(Plain) {
      accept(Plain) {
        post {
          val body = call.receive<String>()
          val response = call.action(body)
          call.response.status(OK)
          call.respond(response)
        }
      }
    }
  }
}
