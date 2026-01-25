import kotlin.String
import kotlinx.serialization.Serializable

@Serializable
public data class ServiceBindingMetadata(
  public val expires_at: String? = null,
  public val renew_before: String? = null,
)
