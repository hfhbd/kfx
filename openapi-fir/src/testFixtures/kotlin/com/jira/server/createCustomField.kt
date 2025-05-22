package com.jira.server

import com.jira.CustomFieldDefinitionJsonBean
import com.jira.FieldBean
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
 * Create a custom field using a definition
 * Creates a custom field using a definition
 */
public fun Route.createCustomField(action: suspend ApplicationCall.(CustomFieldDefinitionJsonBean) -> FieldBean) {
  route(path = """/api/2/field""") {
    contentType(Json) {
      accept(Json) {
        post {
          val body = call.receive<CustomFieldDefinitionJsonBean>()
          val response = call.action(body)
          call.response.status(Created)
          call.respond(response)
        }
      }
    }
  }
}
