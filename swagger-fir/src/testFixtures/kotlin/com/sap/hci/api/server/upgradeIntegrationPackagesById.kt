package com.sap.hci.api.server

import com.sap.hci.api.IntegrationPackageUpdate
import io.ktor.http.HttpStatusCode.Companion.Accepted
import io.ktor.server.application.ApplicationCall
import io.ktor.server.request.receive
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.put
import io.ktor.server.routing.route
import kotlin.Unit

/**
 * You can use the following request to update an existing integration package.<br>
 *
 * In API sandbox, only read APIs can be tested. You need to configure an API endpoint for your account, where you have the required write permissions to update integration package.
 */
public fun Route.upgradeIntegrationPackagesById(action: suspend ApplicationCall.(IntegrationPackageUpdate) -> Unit) {
  route(path = """/IntegrationPackages('{id}')""") {
    put {
      val body = call.receive<IntegrationPackageUpdate>()
      call.action(body)
      call.respond(Accepted)
    }
  }
}
