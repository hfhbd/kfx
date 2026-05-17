package server

import com.example.FooInput
import io.ktor.http.ContentType.Application.Json
import io.ktor.http.HttpStatusCode.Companion.Created
import io.ktor.http.HttpStatusCode.Companion.InternalServerError
import io.ktor.server.application.ApplicationCall
import io.ktor.server.request.receive
import io.ktor.server.response.`header`
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.accept
import io.ktor.server.routing.contentType
import io.ktor.server.routing.query
import io.ktor.server.routing.route
import results.QueryHttpFooBarBazResult

/**
 * Foo Bar API
 */
public fun Route.queryHttpFooBarBaz(action: suspend ApplicationCall.(FooInput) -> QueryHttpFooBarBazResult) {
  route(path = """/http/foo/bar/baz""") {
    contentType(Json) {
      accept(Json) {
        query {
          val body = call.receive<FooInput>()
          val response = call.action(body)
          when (response) {
            is QueryHttpFooBarBazResult.Success -> {
              if (response.logid != null) {
                call.response.`header`("logid", response.logid)
              }
              call.response.status(Created)
              call.respond(response.body)
            }
            is QueryHttpFooBarBazResult.Failure -> {
              if (response.logid != null) {
                call.response.`header`("logid", response.logid)
              }
              call.response.status(InternalServerError)
              call.respond(response.body)
            }
          }
        }
      }
    }
  }
}
