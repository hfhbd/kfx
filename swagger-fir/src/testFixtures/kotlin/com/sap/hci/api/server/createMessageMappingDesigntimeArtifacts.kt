package com.sap.hci.api.server

import com.sap.hci.api.CreateMessageMappingDesigntimeArtifacts
import com.sap.hci.api.ValueMappingDesigntimeArtifactCreate
import io.ktor.http.ContentType.Application.Json
import io.ktor.http.HttpStatusCode.Companion.Created
import io.ktor.server.application.ApplicationCall
import io.ktor.server.request.receive
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.contentType
import io.ktor.server.routing.post
import io.ktor.server.routing.route

/**
 * You can use the following request to create/upload a message mapping. 
 * In API sandbox, only read APIs can be tested. You need to configure an API endpoint for your account, where you have the required write permissions to upload message mappings.
 */
public fun Route.createMessageMappingDesigntimeArtifacts(action: suspend ApplicationCall.(ValueMappingDesigntimeArtifactCreate) -> CreateMessageMappingDesigntimeArtifacts) {
  route(path = """/MessageMappingDesigntimeArtifacts""") {
    contentType(Json) {
      post {
        val body = call.receive<ValueMappingDesigntimeArtifactCreate>()
        val response = call.action(body)
        call.response.status(Created)
        call.respond(response)
      }
    }
  }
}
