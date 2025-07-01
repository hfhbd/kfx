package com.sap.hci.api.server

import com.sap.hci.api.GetIntegrationPackagesByIdScriptCollectionDesigntimeArtifacts
import io.ktor.http.HttpStatusCode.Companion.OK
import io.ktor.server.application.ApplicationCall
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.`get`
import io.ktor.server.routing.route

/**
 * You can use the following request to get all script collections of an integration package by package Id.
 */
public fun Route.getIntegrationPackagesByIdScriptCollectionDesigntimeArtifacts(action: suspend ApplicationCall.() -> GetIntegrationPackagesByIdScriptCollectionDesigntimeArtifacts) {
  route(path = """/IntegrationPackages('{id}')/ScriptCollectionDesigntimeArtifacts""") {
    `get` {
      val response = call.action()
      call.response.status(OK)
      call.respond(response)
    }
  }
}
