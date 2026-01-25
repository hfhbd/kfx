import kotlin.String
import kotlinx.serialization.Serializable

@Serializable
public data class AsyncOperation(
  public val operation: String? = null,
)
