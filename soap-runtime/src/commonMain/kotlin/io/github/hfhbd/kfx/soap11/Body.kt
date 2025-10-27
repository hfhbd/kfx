package io.github.hfhbd.kfx.soap11

import kotlinx.serialization.Serializable
import nl.adaptivity.xmlutil.serialization.XmlElement
import nl.adaptivity.xmlutil.serialization.XmlSerialName

@Serializable
@XmlSerialName("Body", SOAP_11_NAMESPACE)
public data class Body<T>(
    @XmlElement
    val body: T,
)
