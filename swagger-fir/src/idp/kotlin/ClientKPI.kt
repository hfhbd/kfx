import kotlin.Double
import kotlin.String
import kotlinx.serialization.Serializable

/**
 * A single KPI for a client
 */
@Serializable
public data class ClientKPI(
  /**
   * Human-Readable description of this KPI
   */
  public val description: String? = null,
  /**
   * Unique id for this KPI
   */
  public val id: String? = null,
  /**
   * The unit the value is in
   */
  public val unit: String? = null,
  /**
   * The current numerical value for this KPI
   */
  public val `value`: Double? = null,
)
