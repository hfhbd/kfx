import kotlin.Boolean
import kotlin.String
import kotlinx.serialization.Serializable

/**
 * An entry in the list of available scopes
 */
@Serializable
public data class AvailableScopeListEntry(
  /**
   * Whether this is a built-in or a custom scope
   */
  public val builtin: Boolean? = null,
  /**
   * Name of the scope
   */
  public val name: String? = null,
)
