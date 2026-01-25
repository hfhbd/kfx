package server

import Saml
import io.ktor.http.HttpStatusCode.Companion.OK
import io.ktor.server.application.ApplicationCall
import io.ktor.server.request.receive
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.put
import io.ktor.server.routing.route
import kotlin.String

public fun Route.utilSamlmetadata(action: suspend ApplicationCall.(String) -> Saml) {
  route(path = """/api/v1/saml-metadata-converter""") {
    put {
      val body = call.receive<String>()
      val response = call.action(body)
      call.response.status(OK)
      call.respond(response)
    }
  }
}
