package com.jira.server

import com.jira.ReindexBean
import io.ktor.http.ContentType.Application.Json
import io.ktor.http.HttpStatusCode.Companion.Accepted
import io.ktor.server.application.ApplicationCall
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.accept
import io.ktor.server.routing.post
import io.ktor.server.routing.route

/**
 * Start a reindex operation
 * Kicks off a reindex. Need Admin permissions to perform this reindex.
 */
public fun Route.reindex(action: suspend ApplicationCall.() -> ReindexBean) {
  route(path = """/api/2/reindex""") {
    accept(Json) {
      post {
        val response = call.action()
        call.response.status(Accepted)
        call.respond(response)
      }
    }
  }
}
