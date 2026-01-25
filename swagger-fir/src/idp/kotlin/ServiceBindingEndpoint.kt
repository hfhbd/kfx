import kotlin.String
import kotlin.collections.List
import kotlinx.serialization.Serializable

@Serializable
public data class ServiceBindingEndpoint(
  public val host: String,
  public val ports: List<String>,
  public val protocol: ServiceBindingEndpointProtocol? = null,
)
