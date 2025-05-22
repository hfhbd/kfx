package com.jira.server

import com.jira.ProjectTypeBean
import io.ktor.http.ContentType.Application.Json
import io.ktor.http.HttpStatusCode.Companion.OK
import io.ktor.server.application.ApplicationCall
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.`get`
import io.ktor.server.routing.accept
import io.ktor.server.routing.route

/**
 * Get project type by key
 * Returns the project type with the given key, if it is accessible to the logged in user. This takes into account whether the user is licensed on the Application that defines the project type.
 */
public fun Route.getAccessibleProjectTypeByKey(action: suspend ApplicationCall.() -> ProjectTypeBean) {
  route(path = """/api/2/project/type/{projectTypeKey}/accessible""") {
    accept(Json) {
      `get` {
        val response = call.action()
        call.response.status(OK)
        call.respond(response)
      }
    }
  }
}
