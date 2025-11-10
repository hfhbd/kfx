package io.github.hfhbd.kfx.soap11

import kotlinx.serialization.Serializable
import nl.adaptivity.xmlutil.serialization.XmlElement
import nl.adaptivity.xmlutil.serialization.XmlSerialName

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
    val detail: String? = null,
) : Exception(faultString)
