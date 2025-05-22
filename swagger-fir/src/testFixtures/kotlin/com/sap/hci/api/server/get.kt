package com.sap.hci.api.server

import io.ktor.http.ContentType.Application.Json
import io.ktor.http.HttpStatusCode.Companion.OK
import io.ktor.server.application.ApplicationCall
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.`get`
import io.ktor.server.routing.accept
import io.ktor.server.routing.route
import kotlin.Unit

/**
 * You can use the following request to get the CSRF token for this session, which is required for write access via POST, PUT and DELETE operations. Copy the received X-CSRF-Token from the response header.<br>
 *
 * **In API sandbox this request is not relevant!**
 */
public fun Route.`get`(action: suspend ApplicationCall.() -> Unit) {
  route(path = """/""") {
    accept(Json) {
      `get` {
        call.action()
        call.respond(OK)
      }
    }
  }
}
