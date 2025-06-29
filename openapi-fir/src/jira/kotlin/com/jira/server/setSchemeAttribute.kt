package com.jira.server

import io.ktor.http.ContentType.Text.Plain
import io.ktor.http.HttpStatusCode.Companion.NoContent
import io.ktor.server.application.ApplicationCall
import io.ktor.server.request.receive
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.contentType
import io.ktor.server.routing.put
import io.ktor.server.routing.route
import kotlin.String
import kotlin.Unit

/**
 * Update or insert a scheme attribute
 * Updates or inserts the attribute for a permission scheme specified by permission scheme id. The attribute consists of the key and the value. The value will be converted to Boolean using Boolean#valueOf.
 */
public fun Route.setSchemeAttribute(action: suspend ApplicationCall.(String) -> Unit) {
  route(path = """/api/2/permissionscheme/{permissionSchemeId}/attribute/{key}""") {
    contentType(Plain) {
      put {
        val body = call.receive<String>()
        call.action(body)
        call.respond(NoContent)
      }
    }
  }
}
