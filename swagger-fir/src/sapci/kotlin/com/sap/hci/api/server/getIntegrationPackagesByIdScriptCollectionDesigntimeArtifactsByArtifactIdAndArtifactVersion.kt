package com.sap.hci.api.server

import com.sap.hci.api.ScriptCollectionDesigntimeArtifact
import io.ktor.http.HttpStatusCode.Companion.OK
import io.ktor.server.application.ApplicationCall
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.`get`
import io.ktor.server.routing.route

/**
 * You can use the following request to get script collection of an integration package by package Id, script collection Id and script collection version.
 */
public fun Route.getIntegrationPackagesByIdScriptCollectionDesigntimeArtifactsByArtifactIdAndArtifactVersion(action: suspend ApplicationCall.() -> ScriptCollectionDesigntimeArtifact) {
  route(path = """/IntegrationPackages('{id}')/ScriptCollectionDesigntimeArtifacts(Id='{artifactId}',Version='{artifactVersion}')""") {
    `get` {
      val response = call.action()
      call.response.status(OK)
      call.respond(response)
    }
  }
}
