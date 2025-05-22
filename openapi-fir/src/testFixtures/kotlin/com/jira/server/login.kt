package com.jira.server

import com.jira.AuthParams
import com.jira.AuthSuccess
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
 * Create new user session
 * Creates a new session for a user in Jira. Once a session has been successfully created it can be used to access any of Jira's remote APIs and also the web UI by passing the appropriate HTTP Cookie header. Note that it is generally preferrable to use HTTP BASIC authentication with the REST API. However, this resource may be used to mimic the behaviour of Jira's log-in page (e.g. to display log-in errors to a user).
 */
public fun Route.login(action: suspend ApplicationCall.(AuthParams) -> AuthSuccess) {
  route(path = """/auth/1/session""") {
    contentType(Json) {
      accept(Json) {
        post {
          val body = call.receive<AuthParams>()
          val response = call.action(body)
          call.response.status(OK)
          call.respond(response)
        }
      }
    }
  }
}
