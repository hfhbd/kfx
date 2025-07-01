package com.jira.server

import com.jira.EpicRankRequestBean
import io.ktor.http.ContentType.Application.Json
import io.ktor.http.HttpStatusCode.Companion.NoContent
import io.ktor.server.application.ApplicationCall
import io.ktor.server.request.receive
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.contentType
import io.ktor.server.routing.put
import io.ktor.server.routing.route
import kotlin.Unit

/**
 * Rank an epic relative to another
 * Moves (ranks) an epic before or after a given epic. If rankCustomFieldId is not defined, the default rank field will be used.
 */
public fun Route.rankEpics(action: suspend ApplicationCall.(EpicRankRequestBean) -> Unit) {
  route(path = """/agile/1.0/epic/{epicIdOrKey}/rank""") {
    contentType(Json) {
      put {
        val body = call.receive<EpicRankRequestBean>()
        call.action(body)
        call.respond(NoContent)
      }
    }
  }
}
