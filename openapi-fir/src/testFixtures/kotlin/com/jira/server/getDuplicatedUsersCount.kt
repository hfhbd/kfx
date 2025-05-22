package com.jira.server

import com.jira.UserBean
import io.ktor.http.ContentType.Application.Json
import io.ktor.http.HttpStatusCode.Companion.OK
import io.ktor.server.application.ApplicationCall
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.`get`
import io.ktor.server.routing.accept
import io.ktor.server.routing.route

/**
 * Get duplicated users count
 * Returns a list of users that match the search string. This resource cannot be accessed anonymously.
 * Duplicated means that the user has an account in more than one directory
 * and either more than one account is active or the only active account does not belong to the directory
 * with the highest priority.
 * The data returned by this endpoint is cached for 10 minutes and the cache is flushed when any User Directory
 * is added, removed, enabled, disabled, or synchronized.
 * A System Administrator can also flush the cache manually.
 * Related JAC ticket: https://jira.atlassian.com/browse/JRASERVER-68797
 */
public fun Route.getDuplicatedUsersCount(action: suspend ApplicationCall.() -> UserBean) {
  route(path = """/api/2/user/duplicated/count""") {
    accept(Json) {
      `get` {
        val response = call.action()
        call.response.status(OK)
        call.respond(response)
      }
    }
  }
}
