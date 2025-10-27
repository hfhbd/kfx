package io.github.hfhbd.kfx.soap11

import kotlinx.serialization.Contextual
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import nl.adaptivity.xmlutil.serialization.XmlElement
import nl.adaptivity.xmlutil.serialization.XmlSerialName

@Serializable
@XmlSerialName("Envelope", SOAP_11_NAMESPACE)
public data class Envelope<T>(
    @XmlElement
    @SerialName("Header")
    @Contextual
    val header: Header? = null,

    @XmlElement
    @SerialName("Body")
    val body: Body<T>,
)

public const val SOAP_11_NAMESPACE: String = "http://schemas.xmlsoap.org/soap/envelope/"
