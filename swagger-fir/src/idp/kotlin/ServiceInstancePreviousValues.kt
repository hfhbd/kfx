import kotlin.String
import kotlinx.serialization.Serializable

@Serializable
public data class ServiceInstancePreviousValues(
  public val maintenance_info: MaintenanceInfo? = null,
  public val organization_id: String? = null,
  public val plan_id: String? = null,
  public val service_id: String? = null,
  public val space_id: String? = null,
)
