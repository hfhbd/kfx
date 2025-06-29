package com.sap.hci.api.server

import com.sap.hci.api.IntegrationDesigntimeArtifact
import io.ktor.http.HttpStatusCode.Companion.OK
import io.ktor.server.application.ApplicationCall
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.`get`
import io.ktor.server.routing.route

/**
 * You can use the following request to get an integration flow of an integration package by package Id, integration flow and integration flow version.
 */
public fun Route.getIntegrationPackagesByIdIntegrationDesigntimeArtifactsByArtifactIdAndArtifactVersion(action: suspend ApplicationCall.() -> IntegrationDesigntimeArtifact) {
  route(path = """/IntegrationPackages('{id}')/IntegrationDesigntimeArtifacts(Id='{artifactId}',Version='{artifactVersion}')""") {
    `get` {
      val response = call.action()
      call.response.status(OK)
      call.respond(response)
    }
  }
}
