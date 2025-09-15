package server

import io.ktor.http.HttpStatusCode.Companion.InternalServerError
import io.ktor.http.HttpStatusCode.Companion.OK
import io.ktor.server.application.ApplicationCall
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.head
import io.ktor.server.routing.route
import responses.BazACsrfToken

/**
 * Get the CSRF Token for BazA
 */
public fun Route.bazACsrfToken(action: suspend ApplicationCall.() -> BazACsrfToken) {
  route(path = """/http/foo/bar/baz""") {
    head {
      when (val response = call.action()) {
        is BazACsrfToken.Success -> {
          call.respond(OK)
        }
        is BazACsrfToken.Error -> {
          call.respond(InternalServerError)
        }
      }
    }
  }
}
