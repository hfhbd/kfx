package com.sap.hci.api.server

import com.sap.hci.api.GetBuildAndDeployStatusByTaskId
import io.ktor.http.HttpStatusCode.Companion.OK
import io.ktor.server.application.ApplicationCall
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.`get`
import io.ktor.server.routing.route

/**
 * You can use the following request to get the [Build and Deploy Status](https://help.sap.com/viewer/368c481cd6954bdfa5d0435479fd4eaf/Cloud/en-US/d8934e0d3ab649ecb5ae744663c7962c.html) of an artifact by task Id. For an example scenario that shows you how to use this request in a sequence with other API calls, refer to the SAP Help Portal documentation [Get Runtime Status of Deployed Integration Flow](https://help.sap.com/viewer/368c481cd6954bdfa5d0435479fd4eaf/Cloud/en-US/49c733687c80415aa5b67d58cda99dbf.html).
 */
public fun Route.getBuildAndDeployStatusByTaskId(action: suspend ApplicationCall.() -> GetBuildAndDeployStatusByTaskId) {
  route(path = """/BuildAndDeployStatus(TaskId='{taskId}')""") {
    `get` {
      val response = call.action()
      call.response.status(OK)
      call.respond(response)
    }
  }
}
