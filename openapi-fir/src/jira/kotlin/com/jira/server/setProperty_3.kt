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
 * Update specified issue type's property
 * Sets the value of the specified issue type's property
 */
public fun Route.setProperty_3(action: suspend ApplicationCall.(PropertyBean) -> Unit) {
  route(path = """/api/2/issuetype/{issueTypeId}/properties/{propertyKey}""") {
    contentType(Json) {
      put {
        val body = call.receive<PropertyBean>()
        call.action(body)
        call.respond(OK)
      }
    }
  }
}
