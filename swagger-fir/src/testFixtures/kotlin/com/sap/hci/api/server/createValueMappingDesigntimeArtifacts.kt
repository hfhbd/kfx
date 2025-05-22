package com.sap.hci.api.server

import com.sap.hci.api.CreateValueMappingDesigntimeArtifacts
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
 * You can use the following request to upload a value mapping. 
 * In API sandbox, only read APIs can be tested. You need to configure an API endpoint for your account, where you have the required write permissions to upload value mappings.
 */
public fun Route.createValueMappingDesigntimeArtifacts(action: suspend ApplicationCall.(ValueMappingDesigntimeArtifactCreate) -> CreateValueMappingDesigntimeArtifacts) {
  route(path = """/ValueMappingDesigntimeArtifacts""") {
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
