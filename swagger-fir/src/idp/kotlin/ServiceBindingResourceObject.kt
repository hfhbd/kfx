import kotlin.String
import kotlinx.serialization.Serializable

@Serializable
public data class ServiceBindingResourceObject(
  public val app_guid: String? = null,
  public val route: String? = null,
)
