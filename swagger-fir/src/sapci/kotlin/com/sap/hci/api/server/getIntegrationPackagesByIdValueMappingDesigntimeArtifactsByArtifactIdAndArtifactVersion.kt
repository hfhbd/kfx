package com.sap.hci.api.server

import com.sap.hci.api.ValueMappingDesigntimeArtifact
import io.ktor.http.HttpStatusCode.Companion.OK
import io.ktor.server.application.ApplicationCall
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.`get`
import io.ktor.server.routing.route

/**
 * You can use the following request to get a value mapping of an integration package by package Id, valuen mapping and value mapping version.
 */
public fun Route.getIntegrationPackagesByIdValueMappingDesigntimeArtifactsByArtifactIdAndArtifactVersion(action: suspend ApplicationCall.() -> ValueMappingDesigntimeArtifact) {
  route(path = """/IntegrationPackages('{id}')/ValueMappingDesigntimeArtifacts(Id='{artifactId}',Version='{artifactVersion}')""") {
    `get` {
      val response = call.action()
      call.response.status(OK)
      call.respond(response)
    }
  }
}
