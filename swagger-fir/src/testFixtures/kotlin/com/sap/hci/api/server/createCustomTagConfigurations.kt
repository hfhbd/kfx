package com.sap.hci.api.server

import com.sap.hci.api.CustomTagsConfigurationCreate
import io.ktor.http.ContentType.Application.Json
import io.ktor.http.HttpStatusCode.Companion.Created
import io.ktor.server.application.ApplicationCall
import io.ktor.server.request.receive
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.contentType
import io.ktor.server.routing.post
import io.ktor.server.routing.route
import kotlin.Unit

/**
 * You can use the following request to create or upload custom tags configuration.<br>
 *
 * In API sandbox, only read APIs can be tested. You need to configure an API endpoint for your account, where you have the required write permissions to create integration flows.
 */
public fun Route.createCustomTagConfigurations(action: suspend ApplicationCall.(CustomTagsConfigurationCreate) -> Unit) {
  route(path = """/CustomTagConfigurations""") {
    contentType(Json) {
      post {
        val body = call.receive<CustomTagsConfigurationCreate>()
        call.action(body)
        call.respond(Created)
      }
    }
  }
}
