package com.sap.hci.api.server

import io.ktor.http.ContentType.Application.Json
import io.ktor.http.HttpStatusCode.Companion.OK
import io.ktor.server.application.ApplicationCall
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.contentType
import io.ktor.server.routing.post
import io.ktor.server.routing.route
import kotlin.Unit

/**
 * You can use the following request to update an artifact with new specified version.<br>
 *
 * In API sandbox, only read APIs can be tested. You need to configure an API endpoint for your account, where you have the required write permissions to update integration flows.
 */
public fun Route.createIntegrationDesigntimeArtifactSaveAsVersion(action: suspend ApplicationCall.() -> Unit) {
  route(path = """/IntegrationDesigntimeArtifactSaveAsVersion""") {
    contentType(Json) {
      post {
        call.action()
        call.respond(OK)
      }
    }
  }
}
