package io.github.hfhbd.kfx.soap11

import kotlinx.serialization.Contextual
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import nl.adaptivity.xmlutil.serialization.XmlElement
import nl.adaptivity.xmlutil.serialization.XmlSerialName

@Serializable
@XmlSerialName("Envelope", NAMESPACE)
public data class Envelope<T>(
    @XmlElement
    @SerialName("Header")
    @Contextual
    val header: Header? = null,

    @XmlElement
    @SerialName("Body")
    val body: Body<T>,
)

public const val NAMESPACE: String = "http://schemas.xmlsoap.org/soap/envelope/"
