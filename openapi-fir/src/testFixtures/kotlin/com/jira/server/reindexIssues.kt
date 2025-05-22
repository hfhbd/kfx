package com.jira.server

import com.jira.ReindexBean
import io.ktor.http.ContentType.Application.Json
import io.ktor.http.HttpStatusCode.Companion.OK
import io.ktor.server.application.ApplicationCall
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.accept
import io.ktor.server.routing.post
import io.ktor.server.routing.route

/**
 * Reindex individual issues
 * Reindexes one or more individual issues. Indexing is performed synchronously - the call returns when indexing of the issues has completed or a failure occurs.
 */
public fun Route.reindexIssues(action: suspend ApplicationCall.() -> ReindexBean) {
  route(path = """/api/2/reindex/issue""") {
    accept(Json) {
      post {
        val response = call.action()
        call.response.status(OK)
        call.respond(response)
      }
    }
  }
}
