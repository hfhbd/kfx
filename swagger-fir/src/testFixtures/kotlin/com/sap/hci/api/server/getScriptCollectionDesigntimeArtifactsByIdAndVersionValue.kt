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
 * You can use the following request to download an script collection as zip file. Integration flows of configure-only packages cannot be downloaded.<br>For further details, refer to the SAP Help Portal documentation [OData API: Integration Content](https://help.sap.com/viewer/368c481cd6954bdfa5d0435479fd4eaf/Cloud/en-US/d1679a80543f46509a7329243b595bdb.html)</br>
 */
public fun Route.getScriptCollectionDesigntimeArtifactsByIdAndVersionValue(action: suspend ApplicationCall.() -> Source) {
  route(path = """/ScriptCollectionDesigntimeArtifacts(Id='{id}',Version='{version}')/{$}value""") {
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
