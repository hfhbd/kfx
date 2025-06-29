package com.sap.hci.api.server

import com.sap.hci.api.CustomTagsUpdate
import io.ktor.http.HttpStatusCode.Companion.Accepted
import io.ktor.server.application.ApplicationCall
import io.ktor.server.request.receive
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.put
import io.ktor.server.routing.route
import kotlin.Unit

/**
 * You can use the following request to update a Custom Tag.<br>
 *
 * In API sandbox, only read APIs can be tested. You need to configure an API endpoint for your account, where you have the required  permissions to update Custom Tags.
 */
public fun Route.putIntegrationPackagesByIdLinksCustomTagsByName(action: suspend ApplicationCall.(CustomTagsUpdate) -> Unit) {
  route(path = """/IntegrationPackages('{id}')/{$}links/CustomTags('{name}')""") {
    put {
      val body = call.receive<CustomTagsUpdate>()
      call.action(body)
      call.respond(Accepted)
    }
  }
}
