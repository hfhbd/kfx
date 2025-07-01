package com.jira.server

import com.jira.UserWriteBean
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
 * Create new user
 * Create user. By default created user will not be notified with email. If password field is not set then password will be randomly generated.
 */
public fun Route.createUser(action: suspend ApplicationCall.(UserWriteBean) -> UserWriteBean) {
  route(path = """/api/2/user""") {
    contentType(Json) {
      accept(Json) {
        post {
          val body = call.receive<UserWriteBean>()
          val response = call.action(body)
          call.response.status(Created)
          call.respond(response)
        }
      }
    }
  }
}
