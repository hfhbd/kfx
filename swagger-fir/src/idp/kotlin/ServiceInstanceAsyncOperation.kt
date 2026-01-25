import kotlin.String
import kotlinx.serialization.Serializable

@Serializable
public data class ServiceInstanceAsyncOperation(
  public val dashboard_url: String? = null,
  public val metadata: ServiceInstanceMetadata? = null,
  public val operation: String? = null,
)
