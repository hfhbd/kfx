package com.jira.server

import com.jira.SearchRequestBean
import com.jira.SearchResultsBean
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
 * Perform search with JQL
 * Performs a search using JQL.
 */
public fun Route.searchUsingSearchRequest(action: suspend ApplicationCall.(SearchRequestBean) -> SearchResultsBean) {
  route(path = """/api/2/search""") {
    contentType(Json) {
      accept(Json) {
        post {
          val body = call.receive<SearchRequestBean>()
          val response = call.action(body)
          call.response.status(OK)
          call.respond(response)
        }
      }
    }
  }
}
