package com.sap.hci.api.server

import com.sap.hci.api.IntegrationPackage
import com.sap.hci.api.IntegrationPackageCreate
import io.ktor.http.HttpStatusCode.Companion.Created
import io.ktor.server.application.ApplicationCall
import io.ktor.server.request.receive
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.post
import io.ktor.server.routing.route

/**
 * You can use the following request to create/import an integration package.<br>
 *
 * In API sandbox, only read APIs can be tested. You need to configure an API endpoint for your account, where you have the required write permissions to create integration package.
 */
public fun Route.createIntegrationPackages(action: suspend ApplicationCall.(IntegrationPackageCreate) -> IntegrationPackage) {
  route(path = """/IntegrationPackages""") {
    post {
      val body = call.receive<IntegrationPackageCreate>()
      val response = call.action(body)
      call.response.status(Created)
      call.respond(response)
    }
  }
}
