package io.github.hfhbd.kfx.soap

import kotlinx.serialization.Serializable
import nl.adaptivity.xmlutil.serialization.XmlElement
import nl.adaptivity.xmlutil.serialization.XmlSerialName

@Serializable
@XmlSerialName("Body", "http://schemas.xmlsoap.org/soap/envelope/")
public data class Body<T>(
    @XmlElement
    val body: T,
)
