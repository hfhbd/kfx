package com.jira.server

import com.jira.WorklogChangedSinceBean
import io.ktor.http.ContentType.Application.Json
import io.ktor.http.HttpStatusCode.Companion.OK
import io.ktor.server.application.ApplicationCall
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.`get`
import io.ktor.server.routing.accept
import io.ktor.server.routing.route

/**
 * Returns worklogs deleted since given time.
 * Returns worklogs id and delete time of worklogs that was deleted since given time. The returns set of worklogs is limited to 1000 elements. This API will not return worklogs deleted during last minute.
 */
public fun Route.getIdsOfWorklogsDeletedSince(action: suspend ApplicationCall.() -> WorklogChangedSinceBean) {
  route(path = """/api/2/worklog/deleted""") {
    accept(Json) {
      `get` {
        val response = call.action()
        call.response.status(OK)
        call.respond(response)
      }
    }
  }
}
