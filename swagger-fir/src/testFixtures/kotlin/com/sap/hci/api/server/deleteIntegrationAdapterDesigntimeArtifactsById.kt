package com.sap.hci.api.server

import io.ktor.http.ContentType.Application.Json
import io.ktor.http.HttpStatusCode.Companion.OK
import io.ktor.server.application.ApplicationCall
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.accept
import io.ktor.server.routing.contentType
import io.ktor.server.routing.delete
import io.ktor.server.routing.route
import kotlin.Unit

/**
 * You can use the following request to delete an integration adapter. <br>
 *
 * This API is available only in Cloud Foundry environment. In API sandbox, only read APIs can be tested. You need to configure an API endpoint for your account, where you have the required write permissions to delete integration adapter.
 */
public fun Route.deleteIntegrationAdapterDesigntimeArtifactsById(action: suspend ApplicationCall.() -> Unit) {
  route(path = """/IntegrationAdapterDesigntimeArtifacts(Id='{id}')""") {
    contentType(Json) {
      accept(Json) {
        delete {
          call.action()
          call.respond(OK)
        }
      }
    }
  }
}
