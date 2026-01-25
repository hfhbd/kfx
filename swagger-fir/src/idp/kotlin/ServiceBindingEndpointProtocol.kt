import kotlin.String
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public enum class ServiceBindingEndpointProtocol {
  @SerialName(value = "tcp")
  Tcp,
  @SerialName(value = "udp")
  Udp,
  @SerialName(value = "all")
  All,
  ;

  override fun toString(): String = serializer().descriptor.getElementName(ordinal)
}
