package com.sap.hci.api.server

import com.sap.hci.api.GetIntegrationPackagesByIdValueMappingDesigntimeArtifacts
import io.ktor.http.HttpStatusCode.Companion.OK
import io.ktor.server.application.ApplicationCall
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.`get`
import io.ktor.server.routing.route

/**
 * You can use the following request to get all value mappings of an integration package by package Id.
 */
public fun Route.getIntegrationPackagesByIdValueMappingDesigntimeArtifacts(action: suspend ApplicationCall.() -> GetIntegrationPackagesByIdValueMappingDesigntimeArtifacts) {
  route(path = """/IntegrationPackages('{id}')/ValueMappingDesigntimeArtifacts""") {
    `get` {
      val response = call.action()
      call.response.status(OK)
      call.respond(response)
    }
  }
}
