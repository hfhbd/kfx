package com.sap.hci.api.server

import com.sap.hci.api.GetMDIDeltaTokenByOperationAndEntityAndVersion
import io.ktor.http.HttpStatusCode.Companion.OK
import io.ktor.server.application.ApplicationCall
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.`get`
import io.ktor.server.routing.route

/**
 * You can use the following request to get the delta token for the SAP Master Data Integration receiver adapter (MDI receiver adapter). This feature is only available with SAP Integration Suite. For more information on the MDI adapter, see SAP Help Portal documentation at [SAP Master Data Integration Receiver Adapter]( https://help.sap.com/docs/integration-suite/sap-integration-suite/e91e373bbb5b49ccbc2977152def61a2.html).
 */
public fun Route.getMDIDeltaTokenByOperationAndEntityAndVersion(action: suspend ApplicationCall.() -> GetMDIDeltaTokenByOperationAndEntityAndVersion) {
  route(path = """/MDIDeltaToken(Operation='{operation}',Entity='{entity}',Version='{version}')""") {
    `get` {
      val response = call.action()
      call.response.status(OK)
      call.respond(response)
    }
  }
}
