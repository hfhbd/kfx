import kotlin.Boolean
import kotlin.String
import kotlinx.serialization.Serializable

@Serializable
public data class LastOperationResource(
  public val description: String? = null,
  public val instance_usable: Boolean? = null,
  public val state: LastOperationResourceState,
  public val update_repeatable: Boolean? = null,
)
