package io.github.hfhbd.kfx.soap

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import nl.adaptivity.xmlutil.serialization.XmlElement
import nl.adaptivity.xmlutil.serialization.XmlSerialName

@Serializable
@XmlSerialName("Envelope", "http://schemas.xmlsoap.org/soap/envelope/")
public data class Envelope<T>(
    @XmlElement
    @SerialName("Header")
    val header: Header? = null,

    @XmlElement
    @SerialName("Body")
    val body: Body<T>,
)
