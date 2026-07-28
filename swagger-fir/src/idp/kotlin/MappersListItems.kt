import kotlin.String
import kotlinx.serialization.Serializable

@Serializable
public data class MappersListItems(
  /**
   * Uniquely identifies the mapper. Use this in the predefinedMappers-field of a client.
   */
  public val id: String? = null,
  /**
   * Describes what this mapper is doing.
   */
  public val name: String? = null,
)
