package com.jira.server

import com.jira.UserAnonymizationValidationBean
import io.ktor.http.ContentType.Application.Json
import io.ktor.http.HttpStatusCode.Companion.OK
import io.ktor.server.application.ApplicationCall
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.`get`
import io.ktor.server.routing.accept
import io.ktor.server.routing.route

/**
 * Get validation for user anonymization rerun
 * Validates user anonymization re-run process.
 */
public fun Route.validateUserAnonymizationRerun(action: suspend ApplicationCall.() -> UserAnonymizationValidationBean) {
  route(path = """/api/2/user/anonymization/rerun""") {
    accept(Json) {
      `get` {
        val response = call.action()
        call.response.status(OK)
        call.respond(response)
      }
    }
  }
}
