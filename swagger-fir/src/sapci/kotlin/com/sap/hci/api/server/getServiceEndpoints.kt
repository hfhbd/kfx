package com.sap.hci.api.server

import com.sap.hci.api.GetServiceEndpoints
import io.ktor.http.HttpStatusCode.Companion.OK
import io.ktor.server.application.ApplicationCall
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.`get`
import io.ktor.server.routing.route

/**
 * You can use the following request to get all endpoints provided for deployed integration flows.<br>For further details, refer to the SAP Help Portal documentation [OData API: Integration Content](https://help.sap.com/viewer/368c481cd6954bdfa5d0435479fd4eaf/Cloud/en-US/d1679a80543f46509a7329243b595bdb.html) and to the following SAP Community blog [SAP Cloud Integration - OData API for accessing the service endpoints](https://blogs.sap.com/2019/03/25/sap-cloud-platform-integration-odata-api-for-accessing-the-service-endpoints/).
 */
public fun Route.getServiceEndpoints(action: suspend ApplicationCall.() -> GetServiceEndpoints) {
  route(path = """/ServiceEndpoints""") {
    `get` {
      val response = call.action()
      call.response.status(OK)
      call.respond(response)
    }
  }
}
