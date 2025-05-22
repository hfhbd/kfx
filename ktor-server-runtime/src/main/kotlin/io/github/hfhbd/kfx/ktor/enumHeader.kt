package io.github.hfhbd.kfx.ktor

import io.ktor.server.request.header
import io.ktor.server.routing.RoutingRequest
import kotlinx.serialization.KSerializer
import kotlinx.serialization.SerializationException
import kotlinx.serialization.encoding.CompositeDecoder
import kotlinx.serialization.serializer
import kotlin.enums.enumEntries

inline fun <reified T : Enum<T>> RoutingRequest.enumHeader(name: String): T? = header(name)?.toEnum<T>()
inline fun <reified T : Enum<T>> RoutingRequest.enumParameter(name: String): T? = queryParameters[name]?.toEnum<T>()

inline fun <reified T : Enum<T>> String.toEnum(
    serializer: KSerializer<T> = serializer(),
): T {
    val index = serializer.descriptor.getElementIndex(this)
    if (index == CompositeDecoder.UNKNOWN_NAME) {
        throw SerializationException("Unknown enum value $this")
    } else {
        return enumEntries<T>()[index]
    }
}
