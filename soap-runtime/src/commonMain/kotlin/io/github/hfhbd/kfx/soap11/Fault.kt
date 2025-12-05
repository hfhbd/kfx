package io.github.hfhbd.kfx.soap11

import kotlinx.serialization.Polymorphic
import kotlinx.serialization.Serializable
import kotlinx.serialization.builtins.serializer
import kotlinx.serialization.modules.SerializersModule
import nl.adaptivity.xmlutil.serialization.XmlElement
import nl.adaptivity.xmlutil.serialization.XmlSerialName
import nl.adaptivity.xmlutil.serialization.XmlValue

@Serializable
@XmlSerialName("Fault", SOAP_11_NAMESPACE)
public data class Fault(
    @XmlElement(true)
    @XmlSerialName("faultcode", "")
    val faultCode: String,

    @XmlElement(true)
    @XmlSerialName("faultstring", "")
    val faultString: String,

    @XmlElement(true)
    @XmlSerialName("faultactor", "")
    val faultActor: String? = null,

    @XmlElement(true)
    @XmlSerialName("detail", "")
    // Not T because exceptions can't contain generics
    val detail: Detail? = null,
) : Exception(faultString) {
    
    @Serializable
    @XmlSerialName("detail", "")
    public data class Detail(@XmlValue val value: @Polymorphic Any)
    
    public companion object {
        public fun serializerModule(): SerializersModule = SerializersModule {
            polymorphic(Any::class, String::class, String.serializer())
        }
    }
}
