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
 * You can use the following request to delete the delta token for the SAP Master Data Integration receiver adapter (MDI receiver adapter). This feature is only available with SAP Integration Suite. For more information on the MDI adapter, see SAP Help Portal documentation at [SAP Master Data Integration Receiver Adapter]( https://help.sap.com/docs/integration-suite/sap-integration-suite/e91e373bbb5b49ccbc2977152def61a2.html).
 */
public fun Route.deleteMDIDeltaTokenByOperationAndEntityAndVersion(action: suspend ApplicationCall.() -> Unit) {
  route(path = """/MDIDeltaToken(Operation='{operation}',Entity='{entity}',Version='{version}')""") {
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
