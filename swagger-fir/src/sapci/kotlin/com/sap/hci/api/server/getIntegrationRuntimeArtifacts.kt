package com.sap.hci.api.server

import com.sap.hci.api.GetIntegrationRuntimeArtifacts
import io.ktor.http.HttpStatusCode.Companion.OK
import io.ktor.server.application.ApplicationCall
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.`get`
import io.ktor.server.routing.route

/**
 * You can use the following request to get all deployed integration artifacts.<br>
 * In API sandbox the following integration flows are deployed:<br>
 * * '__Integration Flow - FAILED DEPLOYMENT__' with deployment error information
 * * '__Integration Flow with MessageStore - COMPLETED PROCESSING__' with attachments and message store entries
 * * '__Integration Flow with Adapter Data - FAILED PROCESSING__' with message processing log error information, attachments, custom header properties and adapater data
 */
public fun Route.getIntegrationRuntimeArtifacts(action: suspend ApplicationCall.() -> GetIntegrationRuntimeArtifacts) {
  route(path = """/IntegrationRuntimeArtifacts""") {
    `get` {
      val response = call.action()
      call.response.status(OK)
      call.respond(response)
    }
  }
}
