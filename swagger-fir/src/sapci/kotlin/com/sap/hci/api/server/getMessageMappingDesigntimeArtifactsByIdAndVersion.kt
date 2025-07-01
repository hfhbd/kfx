package com.sap.hci.api.server

import com.sap.hci.api.GetMessageMappingDesigntimeArtifactsByIdAndVersion
import io.ktor.http.ContentType.Application.Json
import io.ktor.http.HttpStatusCode.Companion.OK
import io.ktor.server.application.ApplicationCall
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.`get`
import io.ktor.server.routing.accept
import io.ktor.server.routing.contentType
import io.ktor.server.routing.route

/**
 * You can use the following request to get a deployed message mapping deployed by Id and version.
 */
public fun Route.getMessageMappingDesigntimeArtifactsByIdAndVersion(action: suspend ApplicationCall.() -> GetMessageMappingDesigntimeArtifactsByIdAndVersion) {
  route(path = """/MessageMappingDesigntimeArtifacts(Id='{id}',Version='{version}')""") {
    contentType(Json) {
      accept(Json) {
        `get` {
          val response = call.action()
          call.response.status(OK)
          call.respond(response)
        }
      }
    }
  }
}
