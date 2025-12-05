package io.github.hfhbd.kfx.soap11

import kotlinx.serialization.Polymorphic
import kotlinx.serialization.Serializable
import kotlinx.serialization.builtins.serializer
import kotlinx.serialization.modules.SerializersModule
import nl.adaptivity.xmlutil.serialization.XmlElement
import nl.adaptivity.xmlutil.serialization.XmlSerialName
import nl.adaptivity.xmlutil.serialization.XmlValue

@ConsistentCopyVisibility
@Serializable
@XmlSerialName("Fault", SOAP_11_NAMESPACE)
public data class Fault private constructor(
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
    private val detailHolder: DetailHolder? = null,
) : Exception(faultString) {
    
    val detail: Any? get() = detailHolder?.detail
    
    public constructor(
        faultCode: String,
        faultString: String,
        faultActor: String? = null,
        detail: Any? = null,
    ) : this(
        faultCode,
        faultString,
        faultActor,
        detailHolder = detail?.let { DetailHolder(it) }
    )
    
    public fun copy(
        faultCode: String = this.faultCode,
        faultString: String = this.faultString,
        faultActor: String? = this.faultActor,
        detail: Any? = this.detail,
    ): Fault = Fault(
        faultCode = faultCode,
        faultString = faultString,
        faultActor = faultActor,
        detail = detail,
    )
    
    public companion object {
        public fun serializerModule(): SerializersModule = SerializersModule {
            polymorphic(Any::class, String::class, String.serializer())
        }
    }
}

@Serializable
@XmlSerialName("detail", "")
private data class DetailHolder(
    @XmlValue
    val detail: @Polymorphic Any
)
