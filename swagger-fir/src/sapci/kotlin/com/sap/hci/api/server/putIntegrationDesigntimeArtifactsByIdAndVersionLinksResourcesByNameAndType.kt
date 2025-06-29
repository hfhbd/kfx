package com.sap.hci.api.server

import com.sap.hci.api.ResourceUpdate
import io.ktor.http.ContentType.Application.Json
import io.ktor.http.HttpStatusCode.Companion.Accepted
import io.ktor.server.application.ApplicationCall
import io.ktor.server.request.receive
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.contentType
import io.ktor.server.routing.put
import io.ktor.server.routing.route
import kotlin.Unit

/**
 * You can use the following request to update a resource of an integration flow.<br>For further details, refer to the SAP Help Portal documentation [OData API: Integration Content](https://help.sap.com/viewer/368c481cd6954bdfa5d0435479fd4eaf/Cloud/en-US/d1679a80543f46509a7329243b595bdb.html) and to the following SAP Community blog [Remote OData APIs for Integration Flows](https://blogs.sap.com/2018/07/06/cloud-integration-remote-odata-apis-for-integration-flows/).<br>
 *
 * In API sandbox, only read APIs can be tested. You need to configure an API endpoint for your account, where you have the required write permissions to update an integration flow.
 */
public fun Route.putIntegrationDesigntimeArtifactsByIdAndVersionLinksResourcesByNameAndType(action: suspend ApplicationCall.(ResourceUpdate) -> Unit) {
  route(path = """/IntegrationDesigntimeArtifacts(Id='{id}',Version='{version}')/{$}links/Resources(Name='{name}',ResourceType='{type}')""") {
    contentType(Json) {
      put {
        val body = call.receive<ResourceUpdate>()
        call.action(body)
        call.respond(Accepted)
      }
    }
  }
}
