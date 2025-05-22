package com.sap.hci.api.server

import com.sap.hci.api.GetMessageMappingDesigntimeArtifacts
import io.ktor.http.HttpStatusCode.Companion.OK
import io.ktor.server.application.ApplicationCall
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.`get`
import io.ktor.server.routing.route

/**
 * You can use the following request to get all message mappings deployed on the tenant.
 */
public fun Route.getMessageMappingDesigntimeArtifacts(action: suspend ApplicationCall.() -> GetMessageMappingDesigntimeArtifacts) {
  route(path = """/MessageMappingDesigntimeArtifacts""") {
    `get` {
      val response = call.action()
      call.response.status(OK)
      call.respond(response)
    }
  }
}
