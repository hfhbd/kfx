package io.github.hfhbd.kfx.wsdl.model

import io.github.hfhbd.kfx.xsd.model.Documentation
import kotlinx.serialization.modules.SerializersModule
import nl.adaptivity.xmlutil.XmlDeclMode
import nl.adaptivity.xmlutil.core.XmlVersion
import nl.adaptivity.xmlutil.serialization.XML

fun xml(
    transformerSerializers: List<SerializersModule> = listOf(),
): XML = XML(
    serializersModule = SerializersModule {
        include(Documentation.serializerModule())
        for (transformer in transformerSerializers) {
            include(transformer)
        }
    },
) {
    repairNamespaces = false
    xmlVersion = XmlVersion.XML10
    xmlDeclMode = XmlDeclMode.Charset
    autoPolymorphic = true
    defaultPolicy {
        ignoreUnknownChildren()
    }
}
