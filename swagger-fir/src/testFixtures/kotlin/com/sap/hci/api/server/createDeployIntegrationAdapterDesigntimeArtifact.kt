package com.sap.hci.api.server

import io.ktor.http.ContentType.Application.Json
import io.ktor.http.HttpStatusCode.Companion.Accepted
import io.ktor.server.application.ApplicationCall
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.contentType
import io.ktor.server.routing.post
import io.ktor.server.routing.route
import kotlin.Unit

/**
 * You can use the following request to deploy an integration adapter.<br>
 *
 * This API is available only in Cloud Foundry environment. In API sandbox, only read APIs can be tested. You need to configure an API endpoint for your account, where you have the required write permissions to deploy integration adapter.
 */
public fun Route.createDeployIntegrationAdapterDesigntimeArtifact(action: suspend ApplicationCall.() -> Unit) {
  route(path = """/DeployIntegrationAdapterDesigntimeArtifact""") {
    contentType(Json) {
      post {
        call.action()
        call.respond(Accepted)
      }
    }
  }
}
