import kotlin.String
import kotlinx.serialization.Serializable

@Serializable
public data class MaintenanceInfo(
  public val description: String? = null,
  public val version: String? = null,
)
