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
 * You can use the following request to delete a resource of a script collection.<br>For further details, refer to the SAP Help Portal documentation [OData API: Integration Content](https://help.sap.com/viewer/368c481cd6954bdfa5d0435479fd4eaf/Cloud/en-US/d1679a80543f46509a7329243b595bdb.html).<br>
 *
 * In API sandbox, only read APIs can be tested. You need to configure an API endpoint for your account, where you have the required write permissions to update integration flows.
 */
public fun Route.deleteScriptCollectionDesigntimeArtifactsByIdAndVersionLinksResourcesByNameAndType(action: suspend ApplicationCall.() -> Unit) {
  route(path = """/ScriptCollectionDesigntimeArtifacts(Id='{id}',Version='{version}')/{$}links/Resources(Name='{name}',ResourceType='{type}')""") {
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
