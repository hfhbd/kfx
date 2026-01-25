import kotlin.String
import kotlinx.serialization.Serializable

@Serializable
public data class DashboardClient(
  public val id: String,
  public val redirect_uri: String? = null,
  public val secret: String,
)
