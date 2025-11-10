package io.github.hfhbd.kfx.soap11

import kotlinx.serialization.Contextual
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import nl.adaptivity.xmlutil.serialization.XmlElement
import nl.adaptivity.xmlutil.serialization.XmlSerialName

@ConsistentCopyVisibility
@Serializable
@XmlSerialName("Envelope", SOAP_11_NAMESPACE)
public data class Envelope<T> private constructor(
    @XmlElement
    @SerialName("Header")
    @Contextual
    public val header: Header? = null,

    @XmlElement
    @SerialName("Body")
    private val bodyHolder: Body<T>,
) {
    public constructor(header: Header? = null, body: T) : this(header, Body(body))

    public val body: T get() = bodyHolder.body

    public fun copy(header: Header? = this.header, body: T): Envelope<T> = copy(
        header = header,
        bodyHolder = Body(body),
    )
}

public const val SOAP_11_NAMESPACE: String = "http://schemas.xmlsoap.org/soap/envelope/"

@Serializable
@XmlSerialName("Body", SOAP_11_NAMESPACE)
private data class Body<T>(
    @XmlElement
    val body: T,
)
