package com.sap.hci.api.server

import io.ktor.http.HttpStatusCode.Companion.OK
import io.ktor.server.application.ApplicationCall
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.`get`
import io.ktor.server.routing.route
import kotlinx.io.Source

/**
 * You can use the following request to download an integration package of as .zip file. Download fails if the package contains one or more artifacts in draft state.
 */
public fun Route.getIntegrationPackagesByIdValue(action: suspend ApplicationCall.() -> Source) {
  route(path = """/IntegrationPackages('{id}')/{$}value""") {
    `get` {
      val response = call.action()
      call.response.status(OK)
      call.respond(response)
    }
  }
}
