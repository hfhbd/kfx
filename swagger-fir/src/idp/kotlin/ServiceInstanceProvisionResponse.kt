import kotlin.String
import kotlinx.serialization.Serializable

@Serializable
public data class ServiceInstanceProvisionResponse(
  public val dashboard_url: String? = null,
  public val metadata: ServiceInstanceMetadata? = null,
)
