package server

import MappersList
import io.ktor.http.HttpStatusCode.Companion.OK
import io.ktor.server.application.ApplicationCall
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.`get`
import io.ktor.server.routing.route
import kotlin.collections.List

public fun Route.mappersList(action: suspend ApplicationCall.() -> List<MappersList>) {
  route(path = """/api/v1/predefined-mappers/{protocol}""") {
    `get` {
      val response = call.action()
      call.response.status(OK)
      call.respond(response)
    }
  }
}
