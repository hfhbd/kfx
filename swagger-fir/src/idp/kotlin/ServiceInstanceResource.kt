import kotlin.String
import kotlin.Unit
import kotlinx.serialization.Serializable

@Serializable
public data class ServiceInstanceResource(
  public val dashboard_url: String? = null,
  public val maintenance_info: MaintenanceInfo? = null,
  public val metadata: ServiceInstanceMetadata? = null,
  public val parameters: Unit? = null,
  public val plan_id: String? = null,
  public val service_id: String? = null,
)
