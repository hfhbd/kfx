package com.jira.server

import com.jira.IssueTypeSchemeBean
import com.jira.IssueTypeSchemeCreateUpdateBean
import io.ktor.http.ContentType.Application.Json
import io.ktor.http.HttpStatusCode.Companion.OK
import io.ktor.server.application.ApplicationCall
import io.ktor.server.request.receive
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.accept
import io.ktor.server.routing.contentType
import io.ktor.server.routing.put
import io.ktor.server.routing.route

/**
 * Update specified issue type scheme from JSON representation
 * Updates the specified issue type scheme from a JSON representation
 */
public fun Route.updateIssueTypeScheme(action: suspend ApplicationCall.(IssueTypeSchemeCreateUpdateBean) -> IssueTypeSchemeBean) {
  route(path = """/api/2/issuetypescheme/{schemeId}""") {
    contentType(Json) {
      accept(Json) {
        put {
          val body = call.receive<IssueTypeSchemeCreateUpdateBean>()
          val response = call.action(body)
          call.response.status(OK)
          call.respond(response)
        }
      }
    }
  }
}
