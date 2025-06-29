package com.sap.hci.api.server

import io.ktor.http.HttpStatusCode.Companion.Created
import io.ktor.server.application.ApplicationCall
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.post
import io.ktor.server.routing.route
import kotlin.Unit

/**
 * You can use the following request to delete a code list mapping along with all of its related code value mappings using function import DeleteValMaps.<br>
 *
 * In API sandbox, only read APIs can be tested. You need to configure an API endpoint for your account, where you have the required write permissions to create value mappings.
 */
public fun Route.postDeleteValMaps(action: suspend ApplicationCall.() -> Unit) {
  route(path = """/DeleteValMaps""") {
    post {
      call.action()
      call.respond(Created)
    }
  }
}
