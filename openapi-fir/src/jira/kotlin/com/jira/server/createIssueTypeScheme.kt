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
import io.ktor.server.routing.post
import io.ktor.server.routing.route

/**
 * Create an issue type scheme from JSON representation
 * Creates an issue type scheme from a JSON representation
 */
public fun Route.createIssueTypeScheme(action: suspend ApplicationCall.(IssueTypeSchemeCreateUpdateBean) -> IssueTypeSchemeBean) {
  route(path = """/api/2/issuetypescheme""") {
    contentType(Json) {
      accept(Json) {
        post {
          val body = call.receive<IssueTypeSchemeCreateUpdateBean>()
          val response = call.action(body)
          call.response.status(OK)
          call.respond(response)
        }
      }
    }
  }
}
