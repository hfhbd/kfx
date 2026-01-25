import kotlin.String
import kotlinx.serialization.Serializable

@Serializable
public data class RoleNameMapping(
  public val from: String? = null,
  public val to: String? = null,
)
