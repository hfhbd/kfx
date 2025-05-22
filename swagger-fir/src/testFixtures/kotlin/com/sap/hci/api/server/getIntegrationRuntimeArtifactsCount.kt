package com.sap.hci.api.server

import io.ktor.http.HttpStatusCode.Companion.OK
import io.ktor.server.application.ApplicationCall
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.`get`
import io.ktor.server.routing.route
import kotlin.Unit

/**
 * You can use the following request to get the number of all deployed integration artifacts.
 */
public fun Route.getIntegrationRuntimeArtifactsCount(action: suspend ApplicationCall.() -> Unit) {
  route(path = """/IntegrationRuntimeArtifacts/{$}count""") {
    `get` {
      call.action()
      call.respond(OK)
    }
  }
}
