import io.github.hfhbd.kfx.soap.Header
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import nl.adaptivity.xmlutil.serialization.XmlElement

@Serializable
@SerialName("Header")
data class MyHeader(
    @XmlElement
    val to: String,
) : Header
