package io.github.hfhbd.kfx.soap

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import nl.adaptivity.xmlutil.serialization.XmlElement
import nl.adaptivity.xmlutil.serialization.XmlSerialName

@Serializable
@XmlSerialName("Fault", "http://schemas.xmlsoap.org/soap/envelope/")
public data class Fault(
    @XmlElement(true)
    @SerialName("faultcode")
    val faultCode: String,

    @XmlElement(true)
    @SerialName("faultstring")
    val faultString: String,

    @XmlElement(true)
    @SerialName("faultactor")
    val faultActor: String? = null,

    @XmlElement(true)
    @SerialName("detail")
    val detail: String? = null,
)
