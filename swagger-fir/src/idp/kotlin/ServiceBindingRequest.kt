import kotlin.String
import kotlin.Unit
import kotlinx.serialization.Serializable

@Serializable
public data class ServiceBindingRequest(
  public val app_guid: String? = null,
  public val bind_resource: ServiceBindingResourceObject? = null,
  public val context: Context? = null,
  public val parameters: Unit? = null,
  public val plan_id: String,
  public val predecessor_binding_id: String? = null,
  public val service_id: String,
)
