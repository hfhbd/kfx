package com.sap.hci.api.server

import com.sap.hci.api.GetIntegrationPackagesByIdMessageMappingDesigntimeArtifactsByArtifactIdAndArtifactVersion
import io.ktor.http.ContentType.Application.Json
import io.ktor.http.HttpStatusCode.Companion.OK
import io.ktor.server.application.ApplicationCall
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.`get`
import io.ktor.server.routing.accept
import io.ktor.server.routing.route

/**
 * Use the following request to get a message mapping of an integration package by package Id, message mapping Id, and message mapping version.
 */
public fun Route.getIntegrationPackagesByIdMessageMappingDesigntimeArtifactsByArtifactIdAndArtifactVersion(action: suspend ApplicationCall.() -> GetIntegrationPackagesByIdMessageMappingDesigntimeArtifactsByArtifactIdAndArtifactVersion) {
  route(path = """/IntegrationPackages('{id}')/MessageMappingDesigntimeArtifacts(Id='{artifactId}',Version='{artifactVersion}')""") {
    accept(Json) {
      `get` {
        val response = call.action()
        if (call.response.status() == null) {
          call.response.status(OK)
        }
        call.respond(response)
      }
    }
  }
}
