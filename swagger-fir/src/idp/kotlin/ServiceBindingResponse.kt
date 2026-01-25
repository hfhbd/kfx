import kotlin.String
import kotlin.Unit
import kotlin.collections.List
import kotlinx.serialization.Serializable

@Serializable
public data class ServiceBindingResponse(
  public val credentials: Unit? = null,
  public val endpoints: List<ServiceBindingEndpoint>,
  public val metadata: ServiceBindingMetadata? = null,
  public val route_service_url: String? = null,
  public val syslog_drain_url: String? = null,
  public val volume_mounts: List<ServiceBindingVolumeMount>,
)
