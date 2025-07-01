package com.sap.hci.api.server

import com.sap.hci.api.GetCustomTagConfigurationsByCustomTagsValue
import io.ktor.http.HttpStatusCode.Companion.OK
import io.ktor.server.application.ApplicationCall
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.`get`
import io.ktor.server.routing.route

/**
 * You can use the following request to get all custom tags.
 */
public fun Route.getCustomTagConfigurationsByCustomTagsValue(action: suspend ApplicationCall.() -> GetCustomTagConfigurationsByCustomTagsValue) {
  route(path = """/CustomTagConfigurations('CustomTags')/{$}value""") {
    `get` {
      val response = call.action()
      call.response.status(OK)
      call.respond(response)
    }
  }
}
