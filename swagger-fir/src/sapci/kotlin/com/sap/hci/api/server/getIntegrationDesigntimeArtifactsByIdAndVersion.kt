package com.sap.hci.api.server

import com.sap.hci.api.GetIntegrationDesigntimeArtifactsByIdAndVersion
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
 * You can use the following request to get an integration flow by Id and version.<br>For further details, refer to the SAP Help Portal documentation [OData API: Integration Content](https://help.sap.com/viewer/368c481cd6954bdfa5d0435479fd4eaf/Cloud/en-US/d1679a80543f46509a7329243b595bdb.html) and to the following SAP Community blog [Remote OData APIs for Integration Flows](https://blogs.sap.com/2018/07/06/cloud-integration-remote-odata-apis-for-integration-flows/).<br>
 * In API sandbox, the following integration flows with Id and version are available:
 * * '__IntegrationFlow_FAILED_DEPLOYMENT__' with version '__1.0.0__' 
 * * '__IntegrationFlow_MessageStore_COMPLETED_PROCESSING__' with version '__1.0.0__'
 * * '__IntegrationFlow_AdapterData_FAILED_PROCESSING__' with version '__1.0.0__'
 * * '__IntegrationFlowWithConfiguration__' with version '__1.0.5__'
 */
public fun Route.getIntegrationDesigntimeArtifactsByIdAndVersion(action: suspend ApplicationCall.() -> GetIntegrationDesigntimeArtifactsByIdAndVersion) {
  route(path = """/IntegrationDesigntimeArtifacts(Id='{id}',Version='{version}')""") {
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
