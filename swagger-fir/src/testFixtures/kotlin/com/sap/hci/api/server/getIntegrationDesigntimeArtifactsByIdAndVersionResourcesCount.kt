package com.sap.hci.api.server

import io.ktor.http.HttpStatusCode.Companion.OK
import io.ktor.server.application.ApplicationCall
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.`get`
import io.ktor.server.routing.route
import kotlin.Unit

/**
 * You can use the following request to get the number of all resources for an integration flow.<br>For further details, refer to the SAP Help Portal documentation [OData API: Integration Content](https://help.sap.com/viewer/368c481cd6954bdfa5d0435479fd4eaf/Cloud/en-US/d1679a80543f46509a7329243b595bdb.html) and to the following SAP Community blog [Remote OData APIs for Integration Flows](https://blogs.sap.com/2018/07/06/cloud-integration-remote-odata-apis-for-integration-flows/).
 */
public fun Route.getIntegrationDesigntimeArtifactsByIdAndVersionResourcesCount(action: suspend ApplicationCall.() -> Unit) {
  route(path = """/IntegrationDesigntimeArtifacts(Id='{id}',Version='{version}')/Resources/{$}count""") {
    `get` {
      call.action()
      call.respond(OK)
    }
  }
}
