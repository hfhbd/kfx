package com.example.foo.server

import com.example.bar.Bar
import com.example.foo.Foo
import io.github.hfhbd.kfx.soap.soapAction
import io.ktor.http.ContentType.Application.Soap
import io.ktor.http.HttpStatusCode.Companion.OK
import io.ktor.server.application.ApplicationCall
import io.ktor.server.request.receive
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.accept
import io.ktor.server.routing.contentType
import io.ktor.server.routing.post

/**
 * Create Foo
 */
public fun Route.createFoo(action: suspend ApplicationCall.(Foo) -> Bar) {
  contentType(Soap) {
    accept(Soap) {
      soapAction("http://example.com/foo/FooServicePortType/CreateFoo") {
        post {
          val body = call.receive<Foo>()
          val response = call.action(body)
          call.response.status(OK)
          call.respond(response)
        }
      }
    }
  }
}
