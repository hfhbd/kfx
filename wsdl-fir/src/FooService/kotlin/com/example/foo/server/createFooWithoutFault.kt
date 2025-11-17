package com.example.foo.server

import com.example.foo.Foo
import com.example.foo.results.CreateFooWithoutFaultResult
import io.github.hfhbd.kfx.soap11.Envelope
import io.github.hfhbd.kfx.soap11.soapAction
import io.ktor.http.ContentType.Text.Xml
import io.ktor.http.HttpStatusCode.Companion.InternalServerError
import io.ktor.http.HttpStatusCode.Companion.OK
import io.ktor.server.application.ApplicationCall
import io.ktor.server.request.receive
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.accept
import io.ktor.server.routing.contentType
import io.ktor.server.routing.post

/**
 * Create Foo using default SOAP Fault
 */
public fun Route.createFooWithoutFault(action: suspend ApplicationCall.(Envelope<Foo>) -> CreateFooWithoutFaultResult) {
  contentType(Xml) {
    accept(Xml) {
      soapAction("http://example.com/FooService/createFooWithoutFault") {
        post {
          val body = call.receive<Envelope<Foo>>()
          val response = call.action(body)
          when (response) {
            is CreateFooWithoutFaultResult.Success -> {
              call.response.status(OK)
              call.respond(response.body)
            }
            is CreateFooWithoutFaultResult.Failure -> {
              call.response.status(InternalServerError)
              call.respond(response.body)
            }
          }
        }
      }
    }
  }
}
