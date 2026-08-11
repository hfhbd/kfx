package server

import io.ktor.http.HttpStatusCode.Companion.InternalServerError
import io.ktor.http.HttpStatusCode.Companion.OK
import io.ktor.server.application.ApplicationCall
import io.ktor.server.response.`header`
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.head
import io.ktor.server.routing.route
import results.BazACsrfTokenResult

/**
 * Get the CSRF Token for BazA
 */
public fun Route.bazACsrfToken(action: suspend ApplicationCall.() -> BazACsrfTokenResult) {
  route(path = """/http/foo/bar/baz""") {
    head {
      val response = call.action()
      when (response) {
        is BazACsrfTokenResult.Success -> {
          call.response.`header`("X-CSRF-Token", response.XCSRFToken)
          call.respond(OK)
        }
        is BazACsrfTokenResult.Failure -> {
          call.response.status(InternalServerError)
          call.respond(response.body)
        }
      }
    }
  }
}
