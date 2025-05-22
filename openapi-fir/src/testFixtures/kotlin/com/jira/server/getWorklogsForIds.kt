package com.jira.server

import com.jira.Worklog
import com.jira.WorklogIdsRequestBean
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

/**
 * Returns worklogs for given ids.
 * Returns worklogs for given worklog ids. Only worklogs to which the calling user has permissions, will be included in the result. The returns set of worklogs is limited to 1000 elements.
 */
public fun Route.getWorklogsForIds(action: suspend ApplicationCall.(WorklogIdsRequestBean) -> Worklog) {
  route(path = """/api/2/worklog/list""") {
    contentType(Json) {
      accept(Json) {
        post {
          val body = call.receive<WorklogIdsRequestBean>()
          val response = call.action(body)
          call.response.status(OK)
          call.respond(response)
        }
      }
    }
  }
}
