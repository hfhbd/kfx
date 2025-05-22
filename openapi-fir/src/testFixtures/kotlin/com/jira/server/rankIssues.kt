package com.jira.server

import com.jira.IssueRankRequestBean
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
 * Rank issues before or after a given issue
 * Moves (ranks) issues before or after a given issue. At most 50 issues may be ranked at once. This operation may fail for some issues, although this will be rare. In that case the 207 status code is returned for the whole response and detailed information regarding each issue is available in the response body. If rankCustomFieldId is not defined, the default rank field will be used.
 */
public fun Route.rankIssues(action: suspend ApplicationCall.(IssueRankRequestBean) -> Unit) {
  route(path = """/agile/1.0/issue/rank""") {
    contentType(Json) {
      put {
        val body = call.receive<IssueRankRequestBean>()
        call.action(body)
        call.respond(NoContent)
      }
    }
  }
}
