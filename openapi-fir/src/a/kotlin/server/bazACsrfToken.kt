package server

import io.ktor.http.HttpStatusCode.Companion.OK
import io.ktor.server.application.ApplicationCall
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.head
import io.ktor.server.routing.route
import kotlin.Unit

/**
 * Get the CSRF Token for BazA
 */
public fun Route.bazACsrfToken(action: suspend ApplicationCall.() -> Unit) {
  route(path = """/http/foo/bar/baz""") {
    head {
      call.action()
      call.respond(OK)
    }
  }
}
