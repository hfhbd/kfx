import kotlin.Exception
import kotlin.Int
import kotlin.String
import kotlinx.serialization.Serializable

@Serializable
public data class APIError(
  /**
   * A machine-readable error-code
   */
  public val code: Int? = null,
  /**
   * A human-readable error-description
   */
  public override val message: String? = null,
) : Exception()
