package com.jira.server

import com.jira.IndexSnapshotPromiseBean
import io.ktor.http.ContentType.Application.Json
import io.ktor.http.HttpStatusCode.Companion.Accepted
import io.ktor.server.application.ApplicationCall
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.accept
import io.ktor.server.routing.post
import io.ktor.server.routing.route

/**
 * Create index snapshot if not in progress
 * Starts taking an index snapshot if no other snapshot creation process is in progress
 */
public fun Route.createIndexSnapshot(action: suspend ApplicationCall.() -> IndexSnapshotPromiseBean) {
  route(path = """/api/2/index-snapshot""") {
    accept(Json) {
      post {
        val response = call.action()
        call.response.status(Accepted)
        call.respond(response)
      }
    }
  }
}
