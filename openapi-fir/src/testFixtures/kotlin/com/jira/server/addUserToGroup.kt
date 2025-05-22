package com.jira.server

import com.jira.GroupBean
import com.jira.UpdateUserToGroupBean
import io.ktor.http.ContentType.Application.Json
import io.ktor.http.HttpStatusCode.Companion.Created
import io.ktor.server.application.ApplicationCall
import io.ktor.server.request.receive
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.accept
import io.ktor.server.routing.contentType
import io.ktor.server.routing.post
import io.ktor.server.routing.route

/**
 * Add a user to a specified group
 * Adds given user to a group
 */
public fun Route.addUserToGroup(action: suspend ApplicationCall.(UpdateUserToGroupBean) -> GroupBean) {
  route(path = """/api/2/group/user""") {
    contentType(Json) {
      accept(Json) {
        post {
          val body = call.receive<UpdateUserToGroupBean>()
          val response = call.action(body)
          call.response.status(Created)
          call.respond(response)
        }
      }
    }
  }
}
