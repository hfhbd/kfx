package com.jira.server

import com.jira.DeleteAndReplaceVersionBean
import io.ktor.http.ContentType.Application.Json
import io.ktor.http.HttpStatusCode.Companion.NoContent
import io.ktor.server.application.ApplicationCall
import io.ktor.server.request.receive
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.contentType
import io.ktor.server.routing.post
import io.ktor.server.routing.route
import kotlin.Unit

/**
 * Delete version and replace values
 * Delete a project version, removed values will be replaced with ones specified by the parameters.
 */
public fun Route.delete_1(action: suspend ApplicationCall.(DeleteAndReplaceVersionBean) -> Unit) {
  route(path = """/api/2/version/{id}/removeAndSwap""") {
    contentType(Json) {
      post {
        val body = call.receive<DeleteAndReplaceVersionBean>()
        call.action(body)
        call.respond(NoContent)
      }
    }
  }
}
