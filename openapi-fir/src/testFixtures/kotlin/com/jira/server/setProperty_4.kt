package com.jira.server

import com.jira.PropertyBean
import io.ktor.http.ContentType.Application.Json
import io.ktor.http.HttpStatusCode.Companion.OK
import io.ktor.server.application.ApplicationCall
import io.ktor.server.request.receive
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.contentType
import io.ktor.server.routing.put
import io.ktor.server.routing.route
import kotlin.Unit

/**
 * Set value of specified project's property
 * Sets the value of the specified project's property. You can use this resource to store a custom data against the project identified by the key or by the id. The user who stores the data is required to have permissions to administer the project.
 */
public fun Route.setProperty_4(action: suspend ApplicationCall.(PropertyBean) -> Unit) {
  route(path = """/api/2/project/{projectIdOrKey}/properties/{propertyKey}""") {
    contentType(Json) {
      put {
        val body = call.receive<PropertyBean>()
        call.action(body)
        call.respond(OK)
      }
    }
  }
}
