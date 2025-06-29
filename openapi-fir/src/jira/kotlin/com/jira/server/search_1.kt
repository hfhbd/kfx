package com.jira.server

import com.jira.SearchResultsBean
import io.ktor.http.ContentType.Application.Json
import io.ktor.http.HttpStatusCode.Companion.OK
import io.ktor.server.application.ApplicationCall
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.`get`
import io.ktor.server.routing.accept
import io.ktor.server.routing.route

/**
 * Get issues using JQL
 * Searches for issues using JQL.
 * Sorting
 * the jql parameter is a full <a href="http://confluence.atlassian.com/display/JIRA/Advanced+Searching">JQL</a>
 * expression, and includes an ORDER BY clause.
 * The fields param (which can be specified multiple times) gives a comma-separated list of fields
 * to include in the response. This can be used to retrieve a subset of fields.
 * A particular field can be excluded by prefixing it with a minus.
 * By default, only navigable (*navigable) fields are returned in this search resource. Note: the default is different
 * in the get-issue resource -- the default there all fields (*all).
 * *all - include all fields
 * *navigable - include just navigable fields
 * summary,comment - include just the summary and comments
 * -description - include navigable fields except the description (the default is *navigable for search)
 * *all,-comment - include everything except comments
 * GET vs POST:
 * If the JQL query is too large to be encoded as a query param you should instead
 * POST to this resource.
 * Expanding Issues in the Search Result:
 * It is possible to expand the issues returned by directly specifying the expansion on the expand parameter passed
 * in to this resources.
 * For instance, to expand the changelog for all the issues on the search result, it is necessary to
 * specify changelog as one of the values to expand.
 */
public fun Route.search_1(action: suspend ApplicationCall.() -> SearchResultsBean) {
  route(path = """/api/2/search""") {
    accept(Json) {
      `get` {
        val response = call.action()
        call.response.status(OK)
        call.respond(response)
      }
    }
  }
}
