package com.sap.hci.api.server

import io.ktor.http.ContentType.Application.Json
import io.ktor.http.HttpStatusCode.Companion.OK
import io.ktor.server.application.ApplicationCall
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.`get`
import io.ktor.server.routing.accept
import io.ktor.server.routing.contentType
import io.ktor.server.routing.route
import kotlin.Unit

/**
 * You can use the following request to get error information of a deployed integration artifact by Id.<br>
 *
 * In API sandbox, only the deployed integration flow with Id '__IntegrationFlow_FAILED_DEPLOYMENT__' provides the required error information.
 */
public fun Route.getIntegrationRuntimeArtifactsByIdErrorInformationValue(action: suspend ApplicationCall.() -> Unit) {
  route(path = """/IntegrationRuntimeArtifacts('{id}')/ErrorInformation/{$}value""") {
    contentType(Json) {
      accept(Json) {
        `get` {
          call.action()
          call.respond(OK)
        }
      }
    }
  }
}
