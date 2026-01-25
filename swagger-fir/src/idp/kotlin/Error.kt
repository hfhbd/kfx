import kotlin.Boolean
import kotlin.String
import kotlinx.serialization.Serializable

@Serializable
public data class Error(
  public val description: String? = null,
  public val error: String? = null,
  public val instance_usable: Boolean? = null,
  public val update_repeatable: Boolean? = null,
)
