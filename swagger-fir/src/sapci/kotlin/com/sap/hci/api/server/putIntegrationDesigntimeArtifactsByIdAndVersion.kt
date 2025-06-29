package com.sap.hci.api.server

import com.sap.hci.api.IntegrationDesigntimeArtifactUpdate
import io.ktor.http.ContentType.Application.Json
import io.ktor.http.HttpStatusCode.Companion.OK
import io.ktor.server.application.ApplicationCall
import io.ktor.server.request.receive
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.accept
import io.ktor.server.routing.contentType
import io.ktor.server.routing.put
import io.ktor.server.routing.route
import kotlin.Unit

/**
 * You can use the following request to update an integration flow.<br>
 *
 * In API sandbox, only read APIs can be tested. You need to configure an API endpoint for your account, where you have the required write permissions to update integration flows.
 */
public fun Route.putIntegrationDesigntimeArtifactsByIdAndVersion(action: suspend ApplicationCall.(IntegrationDesigntimeArtifactUpdate) -> Unit) {
  route(path = """/IntegrationDesigntimeArtifacts(Id='{id}',Version='{version}')""") {
    contentType(Json) {
      accept(Json) {
        put {
          val body = call.receive<IntegrationDesigntimeArtifactUpdate>()
          call.action(body)
          call.respond(OK)
        }
      }
    }
  }
}
