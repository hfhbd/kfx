package com.sap.hci.api.server

import com.sap.hci.api.CreateIntegrationAdapterDesigntimeArtifacts
import com.sap.hci.api.IntegrationAdapterDesigntimeArtifactImport
import io.ktor.http.ContentType.Application.Json
import io.ktor.http.HttpStatusCode.Companion.Created
import io.ktor.server.application.ApplicationCall
import io.ktor.server.request.receive
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.contentType
import io.ktor.server.routing.post
import io.ktor.server.routing.route

/**
 * You can use the following request to import an integration adapter artifact.<br>
 *
 * This API is available only in the Cloud Foundry environment. <br>For more information, see SAP Help Portal documentation at [Environment-Specific Aspects Integration Developers Should Know](https://help.sap.com/docs/CLOUD_INTEGRATION/368c481cd6954bdfa5d0435479fd4eaf/639a0612e32c498fa7480580d90c9ea6.html?locale=en-US).<br>In API sandbox, only read APIs can be tested. You need to configure an API endpoint for your account, where you have the required write permissions to import adapters.
 */
public fun Route.createIntegrationAdapterDesigntimeArtifacts(action: suspend ApplicationCall.(IntegrationAdapterDesigntimeArtifactImport) -> CreateIntegrationAdapterDesigntimeArtifacts) {
  route(path = """/IntegrationAdapterDesigntimeArtifacts""") {
    contentType(Json) {
      post {
        val body = call.receive<IntegrationAdapterDesigntimeArtifactImport>()
        val response = call.action(body)
        call.response.status(Created)
        call.respond(response)
      }
    }
  }
}
