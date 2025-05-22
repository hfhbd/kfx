package com.sap.hci.api.server

import com.sap.hci.api.MessageMappingDesigntimeArtifactUpdate
import io.ktor.http.ContentType.Application.Json
import io.ktor.http.HttpStatusCode.Companion.OK
import io.ktor.server.application.ApplicationCall
import io.ktor.server.request.receive
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.contentType
import io.ktor.server.routing.put
import io.ktor.server.routing.route
import kotlin.Unit

/**
 * You can use the following request to update a message mapping.<br>
 *
 * In API sandbox, only read APIs can be tested. You need to configure an API endpoint for your account, where you have the required write permissions to update message mappings.
 */
public fun Route.upgradeMessageMappingDesigntimeArtifactsByIdAndVersion(action: suspend ApplicationCall.(MessageMappingDesigntimeArtifactUpdate) -> Unit) {
  route(path = """/MessageMappingDesigntimeArtifacts(Id='{id}',Version='{version}')""") {
    contentType(Json) {
      put {
        val body = call.receive<MessageMappingDesigntimeArtifactUpdate>()
        call.action(body)
        call.respond(OK)
      }
    }
  }
}
