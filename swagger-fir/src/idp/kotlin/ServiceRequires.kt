import kotlin.String
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public enum class ServiceRequires {
  @SerialName(value = "syslog_drain")
  SyslogDrain,
  @SerialName(value = "route_forwarding")
  RouteForwarding,
  @SerialName(value = "volume_mount")
  VolumeMount,
  ;

  override fun toString(): String = serializer().descriptor.getElementName(ordinal)
}
