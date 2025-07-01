package com.jira.server

import com.jira.EditMetaBean
import io.ktor.http.ContentType.Application.Json
import io.ktor.http.HttpStatusCode.Companion.OK
import io.ktor.server.application.ApplicationCall
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.`get`
import io.ktor.server.routing.accept
import io.ktor.server.routing.route

/**
 * Get metadata for issue types used for editing issues
 * Returns the meta data for editing an issue. The fields in the editmeta correspond to the fields in the edit screen for the issue. Fields not in the screen will not be in the editmeta.
 */
public fun Route.getEditIssueMeta(action: suspend ApplicationCall.() -> EditMetaBean) {
  route(path = """/api/2/issue/{issueIdOrKey}/editmeta""") {
    accept(Json) {
      `get` {
        val response = call.action()
        call.response.status(OK)
        call.respond(response)
      }
    }
  }
}
