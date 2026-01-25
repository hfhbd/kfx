import kotlin.Unit
import kotlinx.serialization.Serializable

@Serializable
public data class ServiceInstanceMetadata(
  public val attributes: Unit? = null,
  public val labels: Unit? = null,
)
