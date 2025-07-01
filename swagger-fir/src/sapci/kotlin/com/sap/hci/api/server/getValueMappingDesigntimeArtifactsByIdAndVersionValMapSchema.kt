package com.sap.hci.api.server

import com.sap.hci.api.GetValueMappingDesigntimeArtifactsByIdAndVersionValMapSchema
import io.ktor.http.HttpStatusCode.Companion.OK
import io.ktor.server.application.ApplicationCall
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.`get`
import io.ktor.server.routing.route

/**
 * You can use the following request to get all bidirectional agency identifiers in the Value Mapping.
 */
public fun Route.getValueMappingDesigntimeArtifactsByIdAndVersionValMapSchema(action: suspend ApplicationCall.() -> GetValueMappingDesigntimeArtifactsByIdAndVersionValMapSchema) {
  route(path = """/ValueMappingDesigntimeArtifacts(Id='{id}',Version='{version}')/ValMapSchema""") {
    `get` {
      val response = call.action()
      call.response.status(OK)
      call.respond(response)
    }
  }
}
