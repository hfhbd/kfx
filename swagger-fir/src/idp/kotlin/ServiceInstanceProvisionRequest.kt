import kotlin.String
import kotlin.Unit
import kotlinx.serialization.Serializable

@Serializable
public data class ServiceInstanceProvisionRequest(
  public val context: Context? = null,
  public val maintenance_info: MaintenanceInfo? = null,
  public val organization_guid: String,
  public val parameters: Unit? = null,
  public val plan_id: String,
  public val service_id: String,
  public val space_guid: String,
)
