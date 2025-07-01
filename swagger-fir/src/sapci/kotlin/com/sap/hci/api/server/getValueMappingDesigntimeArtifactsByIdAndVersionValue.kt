package com.sap.hci.api.server

import io.ktor.http.ContentType.Application.Json
import io.ktor.http.ContentType.Application.Zip
import io.ktor.http.HttpStatusCode.Companion.OK
import io.ktor.server.application.ApplicationCall
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.`get`
import io.ktor.server.routing.accept
import io.ktor.server.routing.contentType
import io.ktor.server.routing.route
import kotlinx.io.Source

/**
 * You can use the following request to download a value mapping as zip file. Value mappings of configure-only packages cannot be downloaded. 
 * In API sandbox, the following integration flows with Id and version are available:
 * * '__IntegrationFlow_FAILED_DEPLOYMENT__' with version '__1.0.0__' 
 * * '__IntegrationFlow_MessageStore_COMPLETED_PROCESSING__' with version '__1.0.0__'
 * * '__IntegrationFlow_AdapterData_FAILED_PROCESSING__' with version '__1.0.0__'
 * * '__IntegrationFlowWithConfiguration__' with version '__1.0.5__'
 */
public fun Route.getValueMappingDesigntimeArtifactsByIdAndVersionValue(action: suspend ApplicationCall.() -> Source) {
  route(path = """/ValueMappingDesigntimeArtifacts(Id='{id}',Version='{version}')/{$}value""") {
    contentType(Json) {
      accept(Zip) {
        `get` {
          val response = call.action()
          call.response.status(OK)
          call.respond(response)
        }
      }
    }
  }
}
