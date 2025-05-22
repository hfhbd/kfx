package com.sap.hci.api.server

import com.sap.hci.api.GetValueMappingDesigntimeArtifacts
import io.ktor.http.HttpStatusCode.Companion.OK
import io.ktor.server.application.ApplicationCall
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.`get`
import io.ktor.server.routing.route

/**
 * You can use the following request to get all Value Mappings in the tenant.
 */
public fun Route.getValueMappingDesigntimeArtifacts(action: suspend ApplicationCall.() -> GetValueMappingDesigntimeArtifacts) {
  route(path = """/ValueMappingDesigntimeArtifacts""") {
    `get` {
      val response = call.action()
      call.response.status(OK)
      call.respond(response)
    }
  }
}
