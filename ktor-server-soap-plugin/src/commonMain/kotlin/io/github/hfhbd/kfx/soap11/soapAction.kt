package io.github.hfhbd.kfx.soap11

import io.ktor.http.HttpStatusCode
import io.ktor.server.request.*
import io.ktor.server.routing.*
import io.ktor.server.routing.RouteSelectorEvaluation.Companion.qualityFailedParameter

public fun Route.soapAction(action: String, build: Route.() -> Unit): Route {
    val selector = Soap11RouteSelector(action)
    return createChild(selector).apply(build)
}

private class Soap11RouteSelector(val action: String) : RouteSelector() {
    override suspend fun evaluate(context: RoutingResolveContext, segmentIndex: Int): RouteSelectorEvaluation =
        if (context.call.request.header("SOAPAction") == action) {
            RouteSelectorEvaluation.Success(1.0)
        } else {
            RouteSelectorEvaluation.Failure(qualityFailedParameter, HttpStatusCode.InternalServerError)
        }
}
