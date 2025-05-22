package dev.central.server

import io.ktor.http.ContentType.MultiPart.FormData
import io.ktor.http.ContentType.Text.Plain
import io.ktor.http.HttpStatusCode.Companion.Created
import io.ktor.server.application.ApplicationCall
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.accept
import io.ktor.server.routing.contentType
import io.ktor.server.routing.post
import io.ktor.server.routing.route
import kotlin.String

/**
 * Upload a deployment bundle intended to be published to Maven Central.
 */
public fun Route.uploadComponents(action: suspend ApplicationCall.() -> String) {
  route(path = """/api/v1/publisher/upload""") {
    contentType(FormData) {
      accept(Plain) {
        post {
          val response = call.action()
          call.response.status(Created)
          call.respond(response)
        }
      }
    }
  }
}
