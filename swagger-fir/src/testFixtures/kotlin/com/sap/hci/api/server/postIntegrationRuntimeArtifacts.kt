package com.sap.hci.api.server

import com.sap.hci.api.integrationruntimeartifact.Placeholder
import io.ktor.http.ContentType.Application.Json
import io.ktor.http.HttpStatusCode.Companion.Accepted
import io.ktor.server.application.ApplicationCall
import io.ktor.server.request.receive
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.accept
import io.ktor.server.routing.contentType
import io.ktor.server.routing.post
import io.ktor.server.routing.route
import kotlin.Unit

/**
 * __This method is NOT SUPPORTED in CF__. Please use DeployIntegrationDesigntimeArtifact API in the Integration Flow section!
 */
public fun Route.postIntegrationRuntimeArtifacts(action: suspend ApplicationCall.(Placeholder) -> Unit) {
  route(path = """/IntegrationRuntimeArtifacts""") {
    contentType(Json) {
      accept(Json) {
        post {
          val body = call.receive<Placeholder>()
          call.action(body)
          call.respond(Accepted)
        }
      }
    }
  }
}
