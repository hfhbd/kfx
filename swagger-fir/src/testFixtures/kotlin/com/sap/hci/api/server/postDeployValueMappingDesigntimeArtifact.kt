package com.sap.hci.api.server

import io.ktor.http.ContentType.Application.Json
import io.ktor.http.HttpStatusCode.Companion.Accepted
import io.ktor.server.application.ApplicationCall
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.accept
import io.ktor.server.routing.contentType
import io.ktor.server.routing.post
import io.ktor.server.routing.route
import kotlin.Unit

/**
 * You can use the following request to deploy a value mapping.<br>
 *
 * In API sandbox, only read APIs can be tested. You need to configure an API endpoint for your account, where you have the required write permissions to deploy value mappings.
 */
public fun Route.postDeployValueMappingDesigntimeArtifact(action: suspend ApplicationCall.() -> Unit) {
  route(path = """/DeployValueMappingDesigntimeArtifact""") {
    contentType(Json) {
      accept(Json) {
        post {
          call.action()
          call.respond(Accepted)
        }
      }
    }
  }
}
