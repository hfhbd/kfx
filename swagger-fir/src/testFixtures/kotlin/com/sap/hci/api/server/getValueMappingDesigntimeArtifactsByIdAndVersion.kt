package com.sap.hci.api.server

import com.sap.hci.api.GetValueMappingDesigntimeArtifactsByIdAndVersion
import io.ktor.http.HttpStatusCode.Companion.OK
import io.ktor.server.application.ApplicationCall
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.`get`
import io.ktor.server.routing.route

/**
 * You can use the following request to get a specific Value Mapping in the tenant.
 */
public fun Route.getValueMappingDesigntimeArtifactsByIdAndVersion(action: suspend ApplicationCall.() -> GetValueMappingDesigntimeArtifactsByIdAndVersion) {
  route(path = """/ValueMappingDesigntimeArtifacts(Id='{id}',Version='{version}')""") {
    `get` {
      val response = call.action()
      call.response.status(OK)
      call.respond(response)
    }
  }
}
