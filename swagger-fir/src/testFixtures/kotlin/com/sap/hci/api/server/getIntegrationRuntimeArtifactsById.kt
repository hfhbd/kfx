package com.sap.hci.api.server

import com.sap.hci.api.GetIntegrationRuntimeArtifactsById
import io.ktor.http.ContentType.Application.Json
import io.ktor.http.HttpStatusCode.Companion.OK
import io.ktor.server.application.ApplicationCall
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.`get`
import io.ktor.server.routing.accept
import io.ktor.server.routing.contentType
import io.ktor.server.routing.route

/**
 * You can use the following request to get a deployed integration artifact by Id.<br>
 * In API sandbox integration flows with following Ids are deployed:
 * * '__IntegrationFlow_FAILED_DEPLOYMENT__' with deployment error information
 * * '__IntegrationFlow_MessageStore_COMPLETED_PROCESSING__' with attachments and message store entries
 * * '__IntegrationFlow_AdapterData_FAILED_PROCESSING__' with message processing log error information, attachments, custom header properties and adapter data
 */
public fun Route.getIntegrationRuntimeArtifactsById(action: suspend ApplicationCall.() -> GetIntegrationRuntimeArtifactsById) {
  route(path = """/IntegrationRuntimeArtifacts('{id}')""") {
    contentType(Json) {
      accept(Json) {
        `get` {
          val response = call.action()
          call.response.status(OK)
          call.respond(response)
        }
      }
    }
  }
}
