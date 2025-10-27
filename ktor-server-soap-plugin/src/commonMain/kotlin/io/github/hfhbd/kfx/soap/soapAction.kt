package io.github.hfhbd.kfx.soap

import io.ktor.server.request.*
import io.ktor.server.routing.*

public fun Route.soap11(action: String, build: Route.() -> Unit): Route {
    val selector = Soap11RouteSelector(action)
    return createChild(selector).apply(build)
}

private class Soap11RouteSelector(val action: String) : RouteSelector() {
    override suspend fun evaluate(context: RoutingResolveContext, segmentIndex: Int): RouteSelectorEvaluation =
        if (context.call.request.header("SOAPAction") == action) {
            RouteSelectorEvaluation.Success(1.0)
        } else {
            RouteSelectorEvaluation.Failed
        }
}

public fun Route.soap12(action: String, build: Route.() -> Unit): Route {
    val selector = Soap12RouteSelector(action)
    return createChild(selector).apply(build)
}

private class Soap12RouteSelector(val action: String) : RouteSelector() {
    override suspend fun evaluate(context: RoutingResolveContext, segmentIndex: Int): RouteSelectorEvaluation =
        if (context.call.request.contentType().parameter("action") == action) {
            RouteSelectorEvaluation.Success(1.0)
        } else {
            RouteSelectorEvaluation.Failed
        }
}
