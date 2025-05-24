package io.github.hfhbd.kfx.soap

import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.routing.*
import io.ktor.util.*
import kotlinx.serialization.KSerializer
import kotlinx.serialization.serializer
import nl.adaptivity.xmlutil.serialization.XML

@PublishedApi
internal val SOAP_ACTION: AttributeKey<String> = AttributeKey<String>("SOAP-ACTION")

@PublishedApi
internal val SOAP_FORMAT: AttributeKey<XML> = AttributeKey<XML>("SOAP-FORMAT")

@PublishedApi
internal val SOAP_ENVELOPE_TYPE: AttributeKey<KSerializer<*>> = AttributeKey<KSerializer<*>>("SOAP_ENVELOPE_TYPE")

@PublishedApi
internal val SOAP_FUNCTION: AttributeKey<(Any) -> String> = AttributeKey<(Any) -> String>("SOAP-FUNCTION")

public inline fun <reified EmptyEnvelope : Any> Soap(
    format: XML,
    noinline getAction: (EmptyEnvelope) -> String,
): RouteScopedPlugin<Unit> = createRouteScopedPlugin("SOAP") {
    application.attributes.put(SOAP_FORMAT, format)
    application.attributes.put(SOAP_ENVELOPE_TYPE, serializer<EmptyEnvelope>())
    application.attributes.put(SOAP_FUNCTION, getAction as (Any) -> String)
}

public fun Route.soapAction(action: String, build: Route.() -> Unit): Route {
    val selector = SoapRouteSelector(action)
    return createChild(selector).apply(build)
}

private class SoapRouteSelector(val action: String) : RouteSelector() {
    override suspend fun evaluate(context: RoutingResolveContext, segmentIndex: Int): RouteSelectorEvaluation {
        val call = context.call
        val contentType = call.request.contentType()

        return when (contentType.parameter("action")) {
            action -> RouteSelectorEvaluation.Success(1.0)

            null -> {
                if (SOAP_ACTION !in call.attributes) {
                    val stringBody = call.receiveText()
                    val format = call.application.attributes[SOAP_FORMAT]
                    val type = call.application.attributes[SOAP_ENVELOPE_TYPE]
                    val emptyEnvelope = format.decodeFromString(type, stringBody) as Any
                    val getHeader = call.application.attributes[SOAP_FUNCTION]
                    call.attributes.put(SOAP_ACTION, getHeader(emptyEnvelope))
                }

                val soapAction = context.call.attributes[SOAP_ACTION]
                if (soapAction == action) {
                    RouteSelectorEvaluation.Success(1.0)
                } else {
                    RouteSelectorEvaluation.Failed
                }
            }

            else -> RouteSelectorEvaluation.Failed
        }
    }
}
