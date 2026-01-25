import kotlin.String
import kotlin.Unit
import kotlinx.serialization.Serializable

@Serializable
public data class ServiceInstanceUpdateRequest(
  public val context: Context? = null,
  public val maintenance_info: MaintenanceInfo? = null,
  public val parameters: Unit? = null,
  public val plan_id: String? = null,
  public val previous_values: ServiceInstancePreviousValues? = null,
  public val service_id: String,
)
