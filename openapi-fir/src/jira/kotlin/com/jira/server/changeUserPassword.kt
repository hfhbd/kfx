package com.jira.server

import com.jira.PasswordBean
import io.ktor.http.ContentType.Application.Json
import io.ktor.http.HttpStatusCode.Companion.NoContent
import io.ktor.server.application.ApplicationCall
import io.ktor.server.request.receive
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.contentType
import io.ktor.server.routing.put
import io.ktor.server.routing.route
import kotlin.Unit

/**
 * Update user password
 * Modify user password.
 */
public fun Route.changeUserPassword(action: suspend ApplicationCall.(PasswordBean) -> Unit) {
  route(path = """/api/2/user/password""") {
    contentType(Json) {
      put {
        val body = call.receive<PasswordBean>()
        call.action(body)
        call.respond(NoContent)
      }
    }
  }
}
