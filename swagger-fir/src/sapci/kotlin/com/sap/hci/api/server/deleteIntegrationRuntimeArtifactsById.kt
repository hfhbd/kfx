package com.sap.hci.api.server

import io.ktor.http.ContentType.Application.Json
import io.ktor.http.HttpStatusCode.Companion.Accepted
import io.ktor.server.application.ApplicationCall
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.accept
import io.ktor.server.routing.contentType
import io.ktor.server.routing.delete
import io.ktor.server.routing.route
import kotlin.Unit

/**
 * You can use the following request to undeploy an integration artifact.<br>
 *
 * In API sandbox, only read APIs can be tested. You need to configure an API endpoint for your account, where you have the required write permissions to undeploy an integration artifact.
 */
public fun Route.deleteIntegrationRuntimeArtifactsById(action: suspend ApplicationCall.() -> Unit) {
  route(path = """/IntegrationRuntimeArtifacts('{id}')""") {
    contentType(Json) {
      accept(Json) {
        delete {
          call.action()
          call.respond(Accepted)
        }
      }
    }
  }
}
