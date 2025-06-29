package com.jira.server

import com.jira.LicenseValidationResults
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
import kotlin.String

/**
 * Validate a Jira license
 * Validates a Jira license
 */
public fun Route.validate(action: suspend ApplicationCall.(String) -> LicenseValidationResults) {
  route(path = """/api/2/licenseValidator""") {
    contentType(Json) {
      accept(Json) {
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
