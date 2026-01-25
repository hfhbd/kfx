import kotlin.Boolean
import kotlin.Int
import kotlin.String
import kotlinx.serialization.Serializable

@Serializable
public data class Plan(
  public val bindable: Boolean? = null,
  public val binding_rotatable: Boolean? = null,
  public val description: String,
  public val free: Boolean? = null,
  public val id: String,
  public val maintenance_info: MaintenanceInfo? = null,
  public val maximum_polling_duration: Int? = null,
  public val metadata: Metadata? = null,
  public val name: String,
  public val plan_updateable: Boolean? = null,
  public val schemas: SchemasObject? = null,
)
