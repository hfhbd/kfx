package com.sap.hci.api.server

import com.sap.hci.api.GetIntegrationPackages
import io.ktor.http.HttpStatusCode.Companion.OK
import io.ktor.server.application.ApplicationCall
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.`get`
import io.ktor.server.routing.route

/**
 * You can use the following request to get all integration packages.
 */
public fun Route.getIntegrationPackages(action: suspend ApplicationCall.() -> GetIntegrationPackages) {
  route(path = """/IntegrationPackages""") {
    `get` {
      val response = call.action()
      call.response.status(OK)
      call.respond(response)
    }
  }
}
