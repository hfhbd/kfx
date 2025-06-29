package com.jira.server

import com.jira.TerminologyRequestBean
import io.ktor.http.ContentType.Application.Json
import io.ktor.http.HttpStatusCode.Companion.OK
import io.ktor.server.application.ApplicationCall
import io.ktor.server.request.receive
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.contentType
import io.ktor.server.routing.post
import io.ktor.server.routing.route
import kotlin.Unit

/**
 * Update epic/sprint names from original to new
 * Change epic/sprint names from {originalName} to {newName}. The {newName} will be displayed in Jira instead of {originalName}
 * {"originalName"} must be equal to "epic" or "sprint".
 * There can be only one entry per unique {"originalName"}.
 * {"newName"} can only consist of alphanumeric characters and spaces e.g. {"newName": "iteration number 2"}.
 * {"newName"} must be between 1 to 100 characters.
 * It can't use the already defined {"newName"} values or restricted JQL words.
 * To reset {"newName"} to the default value, enter the {"originalName"} value as the value for {"newName"}. For example, if you want to return to {"originalName": "sprint"}, enter {"newName": "sprint"}.
 */
public fun Route.setTerminologyEntries(action: suspend ApplicationCall.(TerminologyRequestBean) -> Unit) {
  route(path = """/api/2/terminology/entries""") {
    contentType(Json) {
      post {
        val body = call.receive<TerminologyRequestBean>()
        call.action(body)
        call.respond(OK)
      }
    }
  }
}
